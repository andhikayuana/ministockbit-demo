package id.yuana.ministockbit.data.model

data class CoinItemModel(
    val id: String,
    val name: String,
    val fullName: String,
    val raw: RawModel?,
    val display: DisplayModel?
)