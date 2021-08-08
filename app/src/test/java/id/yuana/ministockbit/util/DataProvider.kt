package id.yuana.ministockbit.util

import com.google.gson.Gson
import id.yuana.ministockbit.data.api.response.CoinItem
import id.yuana.ministockbit.data.api.response.WatchlistResponse
import id.yuana.ministockbit.data.api.response.mapToEntity
import id.yuana.ministockbit.data.local.entity.CoinItemEntity
import id.yuana.ministockbit.data.local.entity.mapFromEntity
import id.yuana.ministockbit.data.model.CoinItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataProvider {

    private fun getFromResource(): WatchlistResponse {
        val rawResponseBody =
            MockResponseFileReader("response_success.json").content
        val responseBody = Gson().fromJson(rawResponseBody, WatchlistResponse::class.java)
        return responseBody
    }

    fun getDataFromApi(): List<CoinItem> = getFromResource().data

    fun getDataFromDbEmpty(): Flow<List<CoinItemEntity>> = flowOf(listOf())

    fun getDataModel(): List<CoinItemModel> =
        getDataFromApi().map { it.mapToEntity().mapFromEntity() }
}