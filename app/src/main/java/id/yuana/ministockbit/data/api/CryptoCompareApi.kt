package id.yuana.ministockbit.data.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCompareApi {

    @GET("/data/top/totaltoptiervolfull")
    suspend fun getTopCoins(
        @Query("limit") limit: Int,
        @Query("tsym") tsym: String
    )
}