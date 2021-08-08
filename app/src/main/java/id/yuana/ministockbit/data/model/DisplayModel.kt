package id.yuana.ministockbit.data.model

import com.google.gson.JsonObject


data class DisplayModel(
    val usd: JsonObject
) {

    fun getPrice(): String = this.usd["PRICE"].asString

    fun getChangeHour(): String = this.usd["CHANGEHOUR"].asString

    fun getChangePctHour(): String = this.usd["CHANGEPCTHOUR"].asString
}