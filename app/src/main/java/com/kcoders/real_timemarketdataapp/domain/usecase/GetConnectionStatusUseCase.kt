package com.kcoders.real_timemarketdataapp.domain.usecase

import com.kcoders.real_timemarketdataapp.data.websocket.ConnectionState
import com.kcoders.real_timemarketdataapp.domain.repository.MarketRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetConnectionStatusUseCase : KoinComponent {
    private val repository: MarketRepository by inject()

    operator fun invoke(): Flow<ConnectionState> {
        return repository.getConnectionState()
    }
}