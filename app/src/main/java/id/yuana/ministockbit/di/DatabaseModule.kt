package id.yuana.ministockbit.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.yuana.ministockbit.data.local.MiniStockbitDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "mini_stockbit"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MiniStockbitDatabase {
        return Room.databaseBuilder(
            appContext,
            MiniStockbitDatabase::class.java,
            DB_NAME
        ).build()
    }
}