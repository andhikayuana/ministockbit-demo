package id.yuana.ministockbit.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.yuana.ministockbit.data.model.Account
import id.yuana.ministockbit.data.repository.AccountRepository
import id.yuana.ministockbit.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    val state: MutableLiveData<Resource<Account>> by lazy {
        MutableLiveData<Resource<Account>>(Resource.idle())
    }

    fun onLoginClicked(email: String, password: String) {
        viewModelScope.launch {
            state.value = Resource.loading()
            val result = accountRepository.login(email, password)
            state.value = result

        }
    }
}