package com.kcoders.real_timemarketdataapp.domain.usecase

import com.kcoders.real_timemarketdataapp.domain.model.Trade
import com.kcoders.real_timemarketdataapp.domain.repository.MarketRepository

class SaveTradeUseCase(
    private val repository: MarketRepository
) {
    suspend operator fun invoke(trade: Trade) {
        repository.saveTrade(trade)
    }
}
