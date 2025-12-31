package com.kcoders.real_timemarketdataapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TradeDao {

    @Query("SELECT * FROM trades ORDER BY timestamp DESC LIMIT 50")
    fun getRecentTrades(): Flow<List<TradeEntity>>

    @Insert
    suspend fun insert(trade: TradeEntity)

    @Query("""
        DELETE FROM trades 
        WHERE id NOT IN (
            SELECT id FROM trades ORDER BY timestamp DESC LIMIT 50
        )
    """)
    suspend fun trim()
}
