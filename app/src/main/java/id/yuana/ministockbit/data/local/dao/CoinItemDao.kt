package id.yuana.ministockbit.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import id.yuana.ministockbit.data.local.entity.CoinItemEntity

@Dao
interface CoinItemDao {

    @Query("SELECT * FROM coin_items LIMIT 50")
    suspend fun getWatchlist(): List<CoinItemEntity>

    @Transaction
    @Insert
    suspend fun insert(coinItemEntity: CoinItemEntity)
}