package id.yuana.ministockbit.data.api

import com.google.gson.Gson
import id.yuana.ministockbit.data.api.response.WatchlistResponse
import id.yuana.ministockbit.di.RemoteModule
import id.yuana.ministockbit.util.MockResponseFileReader
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class CryptoCompareApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: CryptoCompareApi

    @Before
    fun setUp() {

        mockWebServer = MockWebServer()
        mockWebServer.start(9090)

        val okHttpClient = RemoteModule.provideOkHttpClient()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://localhost:9090")
            .client(okHttpClient)
            .build()
        api = RemoteModule.provideCryptoCompareApi(retrofit)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun createMockResponse(
        fileName: String,
        responseCode: Int = HttpURLConnection.HTTP_OK
    ) = MockResponse()
        .setResponseCode(responseCode)
        .setBody(MockResponseFileReader(fileName).content)

    @Test
    fun `when getTopCoins with valid params should return success`() {

        //given
        mockWebServer.enqueue(createMockResponse("response_success.json"))

        //when
        val actualResponse = runBlocking {
            api.getTopCoins()
        }

        //then
        assertNotNull(actualResponse)
        actualResponse.body()?.let { actual ->
            val parsed = Gson().fromJson(actual, WatchlistResponse::class.java)

            assertEquals("Success", parsed.message)
            assertEquals(100, parsed.type)
            assertEquals(10, parsed.data.size)
            assertEquals(false, parsed.hasWarning)
        }
    }

    @Test
    fun `when getTopCoins with invalid params should return failed`() {
        //given
        mockWebServer.enqueue(createMockResponse("response_error_tsym_required.json"))

        //when
        val actualResponse = runBlocking {
            api.getTopCoins(tsym = "")
        }

        //then
        assertNotNull(actualResponse)
        actualResponse.body()?.let { actual ->

            assertEquals("Error", actual["Response"].asString)
            assertEquals("tsym", actual["ParamWithError"].asString)
            assertEquals(2, actual["Type"].asInt)
            assertEquals("tsym is a required param.", actual["Message"].asString)

        }
    }

}