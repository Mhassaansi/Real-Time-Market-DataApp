package com.kcoders.real_timemarketdataapp.domain.repository

import com.kcoders.real_timemarketdataapp.data.websocket.ConnectionState
import com.kcoders.real_timemarketdataapp.domain.model.Trade
import kotlinx.coroutines.flow.Flow

interface MarketRepository {
    fun streamTrades(): Flow<Trade>
    fun observeRecentTrades(): Flow<List<Trade>>
    suspend fun saveTrade(trade: Trade)
    fun getConnectionState(): Flow<ConnectionState>
}
