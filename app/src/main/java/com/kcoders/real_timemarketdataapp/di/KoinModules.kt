package com.kcoders.real_timemarketdataapp.di

import androidx.room.Room
import com.kcoders.real_timemarketdataapp.data.local.AppDatabase
import com.kcoders.real_timemarketdataapp.data.repository.MarketRepositoryImpl
import com.kcoders.real_timemarketdataapp.data.websocket.BinanceWebSocketService
import com.kcoders.real_timemarketdataapp.domain.repository.MarketRepository
import com.kcoders.real_timemarketdataapp.domain.usecase.GetConnectionStatusUseCase
import com.kcoders.real_timemarketdataapp.domain.usecase.ObserveTradesUseCase
import com.kcoders.real_timemarketdataapp.domain.usecase.SaveTradeUseCase
import com.kcoders.real_timemarketdataapp.domain.usecase.StreamMarketUseCase
import com.kcoders.real_timemarketdataapp.ui.viewmodel.MarketViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module


fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(repositoryModule,useCaseModule,viewModelModule,databaseModule,dataModule)
}

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "market.db"
        ).build()
    }

    single {
        get<AppDatabase>().tradeDao()
    }
}

val dataModule = module {
    single {
        BinanceWebSocketService()
    }
}


val repositoryModule = module {
    singleOf(::MarketRepositoryImpl).bind(MarketRepository::class)
}

val useCaseModule = module {
    singleOf(::ObserveTradesUseCase)
    singleOf(::StreamMarketUseCase)
    singleOf(::SaveTradeUseCase)
    singleOf(::GetConnectionStatusUseCase)
}
val viewModelModule = module {
    viewModelOf(::MarketViewModel)
}