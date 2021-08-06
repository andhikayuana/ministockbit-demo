package id.yuana.ministockbit.data.repository

import id.yuana.ministockbit.data.local.Cache
import id.yuana.ministockbit.data.model.Account
import id.yuana.ministockbit.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val cache: Cache
) {

    fun isLoggedOn(): Boolean = cache.isLoggedOn()

    suspend fun login(
        email: String,
        password: String
    ): Resource<Account> {
        return withContext(Dispatchers.IO) {
            /**
             * here we go
             * simulate the login process
             */
            delay(1000)

            val account = Account.createDummy()

            if (account.email == email && Account.DUMMY_PASSWORD == password) {
                cache.putAccount(account)
                cache.putLoginStatus(true)
                return@withContext Resource.success(account)
            } else {
                return@withContext Resource.error("Email or Password not valid, please try again")
            }
        }
    }

    suspend fun logout() {
        return withContext(Dispatchers.IO) {
            cache.clear()
        }
    }
}