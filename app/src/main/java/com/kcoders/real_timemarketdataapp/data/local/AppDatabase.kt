package com.kcoders.real_timemarketdataapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TradeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tradeDao(): TradeDao
}
