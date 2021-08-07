package id.yuana.ministockbit.data.local.entity

import androidx.room.Entity
import com.google.gson.JsonObject

@Entity
data class RawEntity(
    val usd: JsonObject
)
