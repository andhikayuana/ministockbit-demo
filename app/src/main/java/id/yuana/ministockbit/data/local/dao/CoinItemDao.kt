package id.yuana.ministockbit.data.local.dao

import androidx.room.*
import id.yuana.ministockbit.data.local.entity.CoinItemEntity

@Dao
interface CoinItemDao {

    @Query("SELECT * FROM coin_items LIMIT 50")
    suspend fun getWatchlist(): List<CoinItemEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coinItemEntity: CoinItemEntity)
}