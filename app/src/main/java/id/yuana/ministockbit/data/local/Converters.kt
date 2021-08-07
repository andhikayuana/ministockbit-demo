package id.yuana.ministockbit.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonObject

class Converters {

    @TypeConverter
    fun fromJson(value: JsonObject?): String? {
        return value?.let {
            value.toString()
        }
    }

    @TypeConverter
    fun toJson(value: String?): JsonObject? {
        return value?.let {
            Gson().fromJson(value, JsonObject::class.java)
        }
    }
}