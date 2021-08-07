package id.yuana.ministockbit.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.yuana.ministockbit.data.repository.AccountRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val hasLogout: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
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