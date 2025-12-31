package com.kcoders.real_timemarketdataapp.domain.usecase

import com.kcoders.real_timemarketdataapp.domain.model.Trade
import com.kcoders.real_timemarketdataapp.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow

class StreamMarketUseCase(
    private val repository: MarketRepository
) {
    operator fun invoke(): Flow<Trade> {
        return repository.streamTrades()
    }
}
