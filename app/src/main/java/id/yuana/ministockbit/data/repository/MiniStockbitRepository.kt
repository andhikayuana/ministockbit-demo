package id.yuana.ministockbit.data.repository

import id.yuana.ministockbit.data.api.CryptoCompareApi
import id.yuana.ministockbit.data.api.response.mapToEntity
import id.yuana.ministockbit.data.local.MiniStockbitDatabase
import id.yuana.ministockbit.data.local.entity.mapFromEntity
import id.yuana.ministockbit.util.networkBoundResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MiniStockbitRepository @Inject constructor(
    private val cryptoCompareApi: CryptoCompareApi,
    private val db: MiniStockbitDatabase,
) {

    fun getWatchlist() = networkBoundResource(
        query = {
            db.coinItemDao()
                .getWatchlist()
                .map { it.map { entity -> entity.mapFromEntity() } }
        },
        fetch = {
            delay(500)
            cryptoCompareApi.getTopCoins()
        },
        saveFetchResult = { response ->
            response.data.forEach {
                db.coinItemDao().insert(it.mapToEntity())
            }
        }
    )

}