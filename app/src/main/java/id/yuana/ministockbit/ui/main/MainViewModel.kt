package id.yuana.ministockbit.ui.main

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.yuana.ministockbit.data.repository.AccountRepository
import id.yuana.ministockbit.data.repository.MiniStockbitRepository
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

    private val refreshTrigger = MutableLiveData(false)

    val watchlistState = Transformations.switchMap(refreshTrigger) {
        miniStockbitRepository.getWatchlist().asLiveData()
    }

    init {
        fetchWatchlist()
    }

    fun fetchWatchlist() {
        refreshTrigger.value = true
    }

    fun logout() {
        viewModelScope.launch {
            accountRepository.logout()
            hasLogout.value = true
        }

    }
}