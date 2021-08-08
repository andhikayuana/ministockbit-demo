package id.yuana.ministockbit.data.repository

import id.yuana.ministockbit.data.local.Cache
import id.yuana.ministockbit.data.local.MiniStockbitDatabase
import id.yuana.ministockbit.data.model.Account
import id.yuana.ministockbit.util.Resource
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AccountRepositoryTest : TestCase() {

    private lateinit var repository: AccountRepository
    private lateinit var cache: Cache
    private lateinit var db: MiniStockbitDatabase

    @Before
    public override fun setUp() {
        cache = mockk()
        db = mockk()

        repository = AccountRepository(cache = cache, db = db)
    }

    @After
    public override fun tearDown() {
        //
    }

    @Test
    fun `when call login with valid params then return success`() {
        runBlocking {
            //given
            val dummyAccount = Account.createDummy()

            justRun { cache.putLoginStatus(true) }
            justRun { cache.putAccount(dummyAccount) }
            every { cache.isLoggedOn() } returns true

            //when
            val result = repository.login(
                email = dummyAccount.email,
                password = Account.DUMMY_PASSWORD
            )
            val isLoggedOn = repository.isLoggedOn()

            //then
            verify { cache.putLoginStatus(any()) }
            verify { cache.putAccount(any()) }
            verify { cache.isLoggedOn() }

            assertEquals(Resource.Status.SUCCESS, result.status)
            result.data?.let {
                assertEquals(dummyAccount.name, it.name)
                assertEquals(dummyAccount.email, it.email)
                assertEquals(dummyAccount.avatar, it.avatar)
            }
            assertEquals(true, isLoggedOn)
        }
    }

    @Test
    fun `when call login with invalid params then return error`() {
        runBlocking {
            //given
            val dummyAccount = Account.createDummy()
            every { cache.isLoggedOn() } returns false

            //when
            val result = repository.login(
                email = dummyAccount.email,
                password = "WRONGPASSWORDHERE"
            )
            val isLoggedOn = repository.isLoggedOn()

            //then
            assertEquals(Resource.Status.ERROR, result.status)
            assertEquals("Email or Password not valid, please try again", result.message)
            assertEquals(false, isLoggedOn)
        }
    }

    @Test
    fun `when call logout then should clear cache and db`() {
        runBlocking {
            //given
            justRun { cache.clear() }
            justRun { db.clearAllTables() }

            //when
            repository.logout()

            //then
            verify { cache.clear() }
            verify { db.clearAllTables() }

        }
    }
}