package com.kcoders.real_timemarketdataapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trades")
data class TradeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val price: Double,
    val quantity: Double,
    val timestamp: Long
)
