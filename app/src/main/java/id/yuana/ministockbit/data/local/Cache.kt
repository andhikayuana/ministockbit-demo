package id.yuana.ministockbit.data.local

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import id.yuana.ministockbit.data.model.Account
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Cache @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        const val CACHE_NAME = "mini_stockbit"
        const val CACHE_IS_LOGGED_ON = "is_logged_on"
        const val CACHE_ACCOUNT = "account"
    }

    private val pref = context.getSharedPreferences(CACHE_NAME, Context.MODE_PRIVATE)

    fun putLoginStatus(isLoggedOn: Boolean): Unit {
        pref.edit {
            putBoolean(CACHE_IS_LOGGED_ON, isLoggedOn)
        }
    }

    fun isLoggedOn(): Boolean = pref.getBoolean(CACHE_IS_LOGGED_ON, false)

    fun putAccount(account: Account): Unit {
        pref.edit {
            putString(CACHE_ACCOUNT, Gson().toJson(account))
        }
    }

    fun getCurrentAccount(): Account {
        val cachedAccount = pref.getString(CACHE_ACCOUNT, "{}")
        return Gson().fromJson(cachedAccount, Account::class.java)
    }

    fun clear() {
        pref.edit().clear().apply()
    }
}