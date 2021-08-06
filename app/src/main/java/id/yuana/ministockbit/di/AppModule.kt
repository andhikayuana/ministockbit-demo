package id.yuana.ministockbit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.yuana.ministockbit.BuildConfig
import id.yuana.ministockbit.data.api.CryptoCompareApi
import id.yuana.ministockbit.data.local.Cache
import id.yuana.ministockbit.data.repository.AccountRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.CRYPTOCOMPARE_BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideCryptoCompareApi(retrofit: Retrofit) = retrofit.create(CryptoCompareApi::class.java)


    @Provides
    @Singleton
    fun provideAccountRepository(cache: Cache) = AccountRepository(cache)
}