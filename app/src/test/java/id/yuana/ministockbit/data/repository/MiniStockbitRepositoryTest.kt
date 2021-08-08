package id.yuana.ministockbit.data.repository

import androidx.lifecycle.asLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import id.yuana.ministockbit.data.api.CryptoCompareApi
import id.yuana.ministockbit.data.api.response.mapToEntity
import id.yuana.ministockbit.data.local.MiniStockbitDatabase
import id.yuana.ministockbit.util.DataProvider
import id.yuana.ministockbit.util.MockResponseFileReader
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.internal.wait
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class MiniStockbitRepositoryTest : TestCase() {

    private lateinit var repository: MiniStockbitRepository
    private lateinit var api: CryptoCompareApi
    private lateinit var db: MiniStockbitDatabase

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    public override fun setUp() {
        api = mockk()
        db = mockk()

        repository = MiniStockbitRepository(api, db)
    }

    @After
    public override fun tearDown() {
    }

    @Test
    fun `when call getWatchlist then should success`() {
        testScope.runBlockingTest {
            //given
            val rawResponseBody =
                MockResponseFileReader("response_success.json").content
            val responseBody = Gson().fromJson(rawResponseBody, JsonObject::class.java)
            val response = Response.success(responseBody)

            coEvery { db.coinItemDao().getWatchlist() } returns DataProvider.getDataFromDbEmpty()
            coEvery { api.getTopCoins() } returns response
            coJustRun {
                DataProvider.getDataFromApi().forEach { db.coinItemDao().insert(it.mapToEntity()) }
            }


            //when
            val result = repository.getWatchlist().toList()


            //then
            coVerify { api.getTopCoins() }
            coVerify { db.coinItemDao().getWatchlist() }
            coVerify {
                DataProvider.getDataFromApi().forEach { db.coinItemDao().insert(it.mapToEntity()) }
            }


        }
    }

}