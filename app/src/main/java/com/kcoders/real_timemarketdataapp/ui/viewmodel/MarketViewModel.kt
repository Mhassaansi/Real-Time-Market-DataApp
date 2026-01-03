package com.kcoders.real_timemarketdataapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kcoders.real_timemarketdataapp.data.websocket.ConnectionState
import com.kcoders.real_timemarketdataapp.domain.repository.MarketRepository
import com.kcoders.real_timemarketdataapp.domain.usecase.GetConnectionStatusUseCase
import com.kcoders.real_timemarketdataapp.domain.usecase.ObserveTradesUseCase
import com.kcoders.real_timemarketdataapp.domain.usecase.SaveTradeUseCase
import com.kcoders.real_timemarketdataapp.domain.usecase.StreamMarketUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MarketViewModel : ViewModel() , KoinComponent {

    private val  streamMarketUseCase : StreamMarketUseCase  by inject()
    private val  observeTradesUseCase : ObserveTradesUseCase by inject()
    private val  saveTradeUseCase : SaveTradeUseCase by inject()
    private val getConnectionStatusUseCase: GetConnectionStatusUseCase by inject()


    private var previousPrice: Double? = null

    private val _price = MutableStateFlow(0.0)
    val price = _price.asStateFlow()

    private val _direction = MutableStateFlow(0)
    val direction = _direction.asStateFlow()

    private val _lastUpdate = MutableStateFlow(0L)
    val lastUpdate = _lastUpdate.asStateFlow()


    val trades = observeTradesUseCase.invoke()

    val connection = getConnectionStatusUseCase.invoke()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ConnectionState.DISCONNECTED
        )

    init {
        viewModelScope.launch {
            streamMarketUseCase.invoke().collect { trade ->
                comparePrice(trade.price)
                saveTradeUseCase.invoke(trade)

                _lastUpdate.value = System.currentTimeMillis()
            }
        }
    }

    private fun comparePrice(newPrice: Double) {
        when {
            previousPrice == null -> _direction.value = 0
            newPrice > previousPrice!! -> _direction.value = 1
            newPrice < previousPrice!! -> _direction.value = -1
            else -> _direction.value = 0
        }
        previousPrice = newPrice
        _price.value = newPrice
    }
}
