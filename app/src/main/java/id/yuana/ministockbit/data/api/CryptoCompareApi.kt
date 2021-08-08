package id.yuana.ministockbit.data.api

import com.google.gson.JsonObject
import id.yuana.ministockbit.BuildConfig.CRYPTOCOMPARE_API_KEY
import id.yuana.ministockbit.data.api.response.WatchlistResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CryptoCompareApi {

    @Headers("Authorization: Apikey $CRYPTOCOMPARE_API_KEY")
    @GET("/data/top/totaltoptiervolfull")
    suspend fun getTopCoins(
        @Query("limit") limit: Int = 50,
        @Query("tsym") tsym: String = "USD"
    ): Response<JsonObject>
}