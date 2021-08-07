package id.yuana.ministockbit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.yuana.ministockbit.data.local.dao.CoinItemDao
import id.yuana.ministockbit.data.local.entity.CoinItemEntity

@Database(
    entities = [CoinItemEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class MiniStockbitDatabase : RoomDatabase() {

    abstract fun coinItemDao(): CoinItemDao
}