package com.kcoders.real_timemarketdataapp

import android.app.Application
import com.kcoders.real_timemarketdataapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class RealTimeApp : Application() ,  KoinComponent{

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@RealTimeApp)
        }
    }

}