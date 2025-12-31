package com.kcoders.real_timemarketdataapp.domain.usecase

import com.kcoders.real_timemarketdataapp.domain.model.Trade
import com.kcoders.real_timemarketdataapp.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow

class ObserveTradesUseCase(
    private val repository: MarketRepository
) {
    operator fun invoke(): Flow<List<Trade>> {
        return repository.observeRecentTrades()
    }
}
