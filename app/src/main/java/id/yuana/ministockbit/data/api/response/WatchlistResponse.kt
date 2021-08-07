package id.yuana.ministockbit.data.api.response

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import id.yuana.ministockbit.data.local.entity.CoinItemEntity
import id.yuana.ministockbit.data.local.entity.DisplayEntity
import id.yuana.ministockbit.data.local.entity.RawEntity

data class WatchlistResponse(
    @SerializedName("Message")
    val message: String,
    @SerializedName("Type")
    val type: Int,
    @SerializedName("RateLimit")
    val rateLimit: JsonObject,
    @SerializedName("HasWarning")
    val hasWarning: Boolean,
    @SerializedName("Data")
    val data: List<CoinItem>
)

data class CoinItem(
    @SerializedName("CoinInfo")
    val coinInfo: CoinInfo,
    @SerializedName("RAW")
    val raw: Raw?,
    @SerializedName("DISPLAY")
    val display: Display?
)

//"Id": "1182",
//"Name": "BTC",
//"FullName": "Bitcoin",
//"Internal": "BTC",
//"ImageUrl": "/media/37746251/btc.png",
//"Url": "/coins/btc/overview",
//"Algorithm": "SHA-256",
//"ProofType": "PoW",
//"Rating": {
//    "Weiss": {
//        "Rating": "B+",
//        "TechnologyAdoptionRating": "A-",
//        "MarketPerformanceRating": "C-"
//    }
//},
//"NetHashesPerSecond": 116159977567820480000,
//"BlockNumber": 694606,
//"BlockTime": 534,
//"BlockReward": 6.25,
//"AssetLaunchDate": "2009-01-03",
//"MaxSupply": 20999999.9769,
//"Type": 1,
//"DocumentType": "Webpagecoinp"
data class CoinInfo(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("FullName")
    val fullName: String
)

//"USD": {
//    "TYPE": "5",
//    "MARKET": "CCCAGG",
//    "FROMSYMBOL": "BTC",
//    "TOSYMBOL": "USD",
//    "FLAGS": "2049",
//    "PRICE": 43618.79,
//    "LASTUPDATE": 1628327827,
//    "MEDIAN": 43604.47,
//    "LASTVOLUME": 0.01123893,
//    "LASTVOLUMETO": 490.3042778829,
//    "LASTTRADEID": "199979810",
//    "VOLUMEDAY": 14221.223576563849,
//    "VOLUMEDAYTO": 616082831.9756716,
//    "VOLUME24HOUR": 47348.54156045,
//    "VOLUME24HOURTO": 2012119840.2912548,
//    "OPENDAY": 42857.65,
//    "HIGHDAY": 43949.78,
//    "LOWDAY": 42617.09,
//    "OPEN24HOUR": 40623.29,
//    "HIGH24HOUR": 43957.66,
//    "LOW24HOUR": 40325.02,
//    "LASTMARKET": "Coinbase",
//    "VOLUMEHOUR": 662.0118530600042,
//    "VOLUMEHOURTO": 28879586.303214088,
//    "OPENHOUR": 43484.62,
//    "HIGHHOUR": 43808.26,
//    "LOWHOUR": 43421.19,
//    "TOPTIERVOLUME24HOUR": 47348.54156045,
//    "TOPTIERVOLUME24HOURTO": 2012119840.2912548,
//    "CHANGE24HOUR": 2995.5,
//    "CHANGEPCT24HOUR": 7.3738488438528735,
//    "CHANGEDAY": 761.1399999999994,
//    "CHANGEPCTDAY": 1.7759723176609061,
//    "CHANGEHOUR": 134.16999999999825,
//    "CHANGEPCTHOUR": 0.3085458720807454,
//    "CONVERSIONTYPE": "direct",
//    "CONVERSIONSYMBOL": "",
//    "SUPPLY": 18778793,
//    "MKTCAP": 819108228320.47,
//    "MKTCAPPENALTY": 0,
//    "TOTALVOLUME24H": 345764.26387354825,
//    "TOTALVOLUME24HTO": 15028652564.564602,
//    "TOTALTOPTIERVOLUME24H": 345384.3937733589,
//    "TOTALTOPTIERVOLUME24HTO": 15012083090.437164,
//    "IMAGEURL": "/media/37746251/btc.png"
//}
data class Raw(
    @SerializedName("USD")
    val usd: JsonObject
)

//"USD": {
//    "FROMSYMBOL": "Ƀ",
//    "TOSYMBOL": "$",
//    "MARKET": "CryptoCompare Index",
//    "PRICE": "$ 43,618.8",
//    "LASTUPDATE": "Just now",
//    "LASTVOLUME": "Ƀ 0.01124",
//    "LASTVOLUMETO": "$ 490.30",
//    "LASTTRADEID": "199979810",
//    "VOLUMEDAY": "Ƀ 14,221.2",
//    "VOLUMEDAYTO": "$ 616,082,832.0",
//    "VOLUME24HOUR": "Ƀ 47,348.5",
//    "VOLUME24HOURTO": "$ 2,012,119,840.3",
//    "OPENDAY": "$ 42,857.7",
//    "HIGHDAY": "$ 43,949.8",
//    "LOWDAY": "$ 42,617.1",
//    "OPEN24HOUR": "$ 40,623.3",
//    "HIGH24HOUR": "$ 43,957.7",
//    "LOW24HOUR": "$ 40,325.0",
//    "LASTMARKET": "Coinbase",
//    "VOLUMEHOUR": "Ƀ 662.01",
//    "VOLUMEHOURTO": "$ 28,879,586.3",
//    "OPENHOUR": "$ 43,484.6",
//    "HIGHHOUR": "$ 43,808.3",
//    "LOWHOUR": "$ 43,421.2",
//    "TOPTIERVOLUME24HOUR": "Ƀ 47,348.5",
//    "TOPTIERVOLUME24HOURTO": "$ 2,012,119,840.3",
//    "CHANGE24HOUR": "$ 2,995.50",
//    "CHANGEPCT24HOUR": "7.37",
//    "CHANGEDAY": "$ 761.14",
//    "CHANGEPCTDAY": "1.78",
//    "CHANGEHOUR": "$ 134.17",
//    "CHANGEPCTHOUR": "0.31",
//    "CONVERSIONTYPE": "direct",
//    "CONVERSIONSYMBOL": "",
//    "SUPPLY": "Ƀ 18,778,793.0",
//    "MKTCAP": "$ 819.11 B",
//    "MKTCAPPENALTY": "0 %",
//    "TOTALVOLUME24H": "Ƀ 345.76 K",
//    "TOTALVOLUME24HTO": "$ 15.03 B",
//    "TOTALTOPTIERVOLUME24H": "Ƀ 345.38 K",
//    "TOTALTOPTIERVOLUME24HTO": "$ 15.01 B",
//    "IMAGEURL": "/media/37746251/btc.png"
//}
data class Display(
    @SerializedName("USD")
    val usd: JsonObject
)

fun CoinItem.mapToEntity(): CoinItemEntity {
    return CoinItemEntity(
        id = this.coinInfo.id,
        name = this.coinInfo.name,
        fullName = this.coinInfo.fullName,
        raw = this.raw?.let { RawEntity(usd = it.usd) },
        display = this.display?.let { DisplayEntity(usd = it.usd) }
    )
}