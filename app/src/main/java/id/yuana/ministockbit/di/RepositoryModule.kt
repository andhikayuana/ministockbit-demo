package id.yuana.ministockbit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.yuana.ministockbit.data.api.CryptoCompareApi
import id.yuana.ministockbit.data.local.Cache
import id.yuana.ministockbit.data.local.MiniStockbitDatabase
import id.yuana.ministockbit.data.repository.AccountRepository
import id.yuana.ministockbit.data.repository.MiniStockbitRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAccountRepository(cache: Cache) = AccountRepository(cache)

    @Provides
    @Singleton
    fun provideMiniStockbitRepository(
        cryptoCompareApi: CryptoCompareApi,
        database: MiniStockbitDatabase
    ) =
        MiniStockbitRepository(cryptoCompareApi, database)
}