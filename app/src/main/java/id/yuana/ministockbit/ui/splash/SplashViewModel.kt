package id.yuana.ministockbit.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.yuana.ministockbit.data.repository.AccountRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    companion object {
        const val IDLE = 0
        const val GOTO_LOGIN = 1
        const val GOTO_MAIN = 2
    }

    val next: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(IDLE)
    }

    init {
        viewModelScope.launch {
            delay(1000)
            val isLoggedOn = accountRepository.isLoggedOn()
            next.value = if (isLoggedOn) GOTO_MAIN else GOTO_LOGIN
        }
    }


}