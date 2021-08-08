package id.yuana.ministockbit.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.yuana.ministockbit.data.model.Account
import id.yuana.ministockbit.data.repository.AccountRepository
import id.yuana.ministockbit.util.CoroutinesTestRule
import id.yuana.ministockbit.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verifyOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    val testCoroutineRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel

    @MockK
    private lateinit var repository: AccountRepository

    @MockK(relaxed = true)
    private lateinit var stateObserver: Observer<Resource<Account>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = LoginViewModel(repository)
        viewModel.state.observeForever(stateObserver)
    }

    @After
    fun tearDown() {
        viewModel.state.removeObserver(stateObserver)
    }

    @Test
    fun `when call login with valid params then should success`() {
        testCoroutineRule.runBlockingTest {
            //given
            val dummyAccount = Account.createDummy()

            coEvery {
                repository.login(
                    dummyAccount.email,
                    Account.DUMMY_PASSWORD
                )
            } returns Resource.success(dummyAccount)

            //when
            viewModel.onLoginClicked(dummyAccount.email, Account.DUMMY_PASSWORD)

            //then
            verifyOrder {
                stateObserver.onChanged(Resource.idle())
                stateObserver.onChanged(Resource.loading())
                stateObserver.onChanged(Resource.success(dummyAccount))
            }
        }
    }

    @Test
    fun `when call login with invalid params then should error`() {
        testCoroutineRule.runBlockingTest {
            //given
            val dummyAccount = Account.createDummy()
            val errorMessage = "Email or Password not valid, please try again"

            coEvery {
                repository.login(dummyAccount.email, "WRONGPASSWORDHERE")
            } returns Resource.error(errorMessage)

            //when
            viewModel.onLoginClicked(dummyAccount.email, "WRONGPASSWORDHERE")

            //then
            verifyOrder {
                stateObserver.onChanged(Resource.idle())
                stateObserver.onChanged(Resource.loading())
                stateObserver.onChanged(Resource.error(errorMessage))
            }
        }
    }
}