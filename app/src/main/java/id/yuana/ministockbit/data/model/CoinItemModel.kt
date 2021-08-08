package id.yuana.ministockbit.data.model

data class CoinItemModel(
    val id: String,
    val name: String,
    val fullName: String,
    val raw: RawModel?,
    val display: DisplayModel?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CoinItemModel

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }
}