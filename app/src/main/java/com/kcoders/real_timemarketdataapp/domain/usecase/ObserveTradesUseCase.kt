package com.kcoders.real_timemarketdataapp.domain.usecase

import com.kcoders.real_timemarketdataapp.domain.model.Trade
import com.kcoders.real_timemarketdataapp.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class ObserveTradesUseCase : KoinComponent {
    private val repository: MarketRepository by inject()

    operator fun invoke(): Flow<List<Trade>> {
        return repository.observeRecentTrades()
    }
}
