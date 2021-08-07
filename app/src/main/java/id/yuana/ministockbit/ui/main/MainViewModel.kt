package id.yuana.ministockbit.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.yuana.ministockbit.data.api.response.CoinItem
import id.yuana.ministockbit.data.repository.AccountRepository
import id.yuana.ministockbit.data.repository.MiniStockbitRepository
import id.yuana.ministockbit.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val miniStockbitRepository: MiniStockbitRepository
) : ViewModel() {

    val hasLogout: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val watchlistState: MutableLiveData<Resource<List<CoinItem>>> by lazy {
        MutableLiveData<Resource<List<CoinItem>>>(Resource.idle())
    }

    init {
        fetchWatchlist()
    }

    fun fetchWatchlist() {
        viewModelScope.launch {
            watchlistState.value = Resource.loading()
            val result = miniStockbitRepository.getWatchlist()
            watchlistState.value = result
        }
    }

    fun logout() {
        viewModelScope.launch {

            //TODO
            accountRepository.logout()

            hasLogout.value = true
        }

    }
    // TODO: Implement the ViewModel
}