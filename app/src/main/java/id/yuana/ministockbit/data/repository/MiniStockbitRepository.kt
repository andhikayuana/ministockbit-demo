package id.yuana.ministockbit.data.repository

import id.yuana.ministockbit.data.api.CryptoCompareApi
import id.yuana.ministockbit.data.api.response.CoinItem
import id.yuana.ministockbit.data.api.response.mapToEntity
import id.yuana.ministockbit.data.local.MiniStockbitDatabase
import id.yuana.ministockbit.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MiniStockbitRepository @Inject constructor(
    private val cryptoCompareApi: CryptoCompareApi,
    private val db: MiniStockbitDatabase,
) {

    suspend fun getWatchlist(): Resource<List<CoinItem>> {
        return withContext(Dispatchers.IO) {
            try {


//                val local = flow<List<CoinItemEntity>> {
//                    db.coinItemDao().getWatchlist()
//                }.map { list -> list.map { it.mapFromEntity() } }
//
//                val remote = flow<List<CoinItem>> {
//
//                }


                val response = cryptoCompareApi.getTopCoins()
                response.body()!!.data.forEach {
                    db.coinItemDao().insert(it.mapToEntity())
                }
                Resource.success(response.body()!!.data)

            } catch (th: Throwable) {
                Resource.error(th.message ?: "Oops something went wrong!")
            }
        }
    }

}