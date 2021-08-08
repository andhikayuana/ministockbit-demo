package id.yuana.ministockbit.data.model

import com.google.gson.JsonObject

data class RawModel(
    val usd: JsonObject
) {

    fun isChangeHourIncrease(): Boolean = this.usd["CHANGEPCTHOUR"].asDouble > 0

    fun isChangeHourDecrease(): Boolean = this.usd["CHANGEPCTHOUR"].asDouble < 0
}
