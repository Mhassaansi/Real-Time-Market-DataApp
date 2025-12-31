package com.kcoders.real_timemarketdataapp.data.repository

import com.kcoders.real_timemarketdataapp.domain.model.Trade
import com.kcoders.real_timemarketdataapp.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow

class MarketRepositoryImpl : MarketRepository {
    override fun streamTrades(): Flow<Trade> {
        TODO("Not yet implemented")
    }

    override fun observeRecentTrades(): Flow<List<Trade>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveTrade(trade: Trade) {
        TODO("Not yet implemented")
    }
}