package id.yuana.ministockbit.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.yuana.ministockbit.data.model.CoinItemModel
import id.yuana.ministockbit.data.model.DisplayModel
import id.yuana.ministockbit.data.model.RawModel

@Entity(
    tableName = "coin_items"
)
data class CoinItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @Embedded(prefix = "raw_")
    val raw: RawEntity?,
    @Embedded(prefix = "diplay_")
    val display: DisplayEntity?
)

fun CoinItemEntity.mapFromEntity(): CoinItemModel {
    return CoinItemModel(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        raw = this.raw?.let { RawModel(usd = it.usd) },
        display = this.display?.let { DisplayModel(usd = it.usd) }
    )
}