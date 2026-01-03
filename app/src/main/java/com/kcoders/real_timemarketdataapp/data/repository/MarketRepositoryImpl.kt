package com.kcoders.real_timemarketdataapp.data.repository

import com.kcoders.real_timemarketdataapp.data.local.TradeDao
import com.kcoders.real_timemarketdataapp.data.mapper.toDomain
import com.kcoders.real_timemarketdataapp.data.mapper.toEntity
import com.kcoders.real_timemarketdataapp.data.websocket.BinanceWebSocketService
import com.kcoders.real_timemarketdataapp.data.websocket.ConnectionState
import com.kcoders.real_timemarketdataapp.domain.model.Trade
import com.kcoders.real_timemarketdataapp.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MarketRepositoryImpl : MarketRepository , KoinComponent {

    private val socket: BinanceWebSocketService by inject()
    private val dao: TradeDao by inject()

    override fun streamTrades(): Flow<Trade> = socket.connect()

    override fun observeRecentTrades(): Flow<List<Trade>> =
        dao.getRecentTrades().map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun saveTrade(trade: Trade) {
        dao.insert(trade.toEntity())
        dao.trim()
    }


    override fun getConnectionState(): Flow<ConnectionState> = socket.state
}


