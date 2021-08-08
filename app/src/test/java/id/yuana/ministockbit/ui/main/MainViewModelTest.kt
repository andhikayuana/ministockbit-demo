package id.yuana.ministockbit.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.yuana.ministockbit.data.model.CoinItemModel
import id.yuana.ministockbit.data.repository.AccountRepository
import id.yuana.ministockbit.data.repository.MiniStockbitRepository
import id.yuana.ministockbit.util.CoroutinesTestRule
import id.yuana.ministockbit.util.DataProvider
import id.yuana.ministockbit.util.Resource
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testCoroutineRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @MockK
    private lateinit var accountRepository: AccountRepository

    @MockK(relaxed = true)
    private lateinit var miniStockbitRepository: MiniStockbitRepository

    @MockK(relaxed = true)
    private lateinit var hasLogoutObserver: Observer<Boolean>

    @MockK(relaxed = true)
    private lateinit var refreshTriggerObserver: Observer<Boolean>

    @MockK(relaxed = true)
    private lateinit var watchlistStateObserver: Observer<Resource<List<CoinItemModel>>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = MainViewModel(
            accountRepository,
            miniStockbitRepository
        )

        viewModel.hasLogout.observeForever(hasLogoutObserver)
        viewModel.refreshTrigger.observeForever(refreshTriggerObserver)
        viewModel.watchlistState.observeForever(watchlistStateObserver)
    }

    @After
    fun tearDown() {

        viewModel.hasLogout.removeObserver(hasLogoutObserver)
        viewModel.refreshTrigger.removeObserver(refreshTriggerObserver)
        viewModel.watchlistState.removeObserver(watchlistStateObserver)
    }

    @Test
    fun `when logout call then should success`() {
        testCoroutineRule.runBlockingTest {
            //given
            coJustRun { accountRepository.logout() }

            //when
            viewModel.logout()

            //then
            coVerifyOrder {
                accountRepository.logout()
                hasLogoutObserver.onChanged(true)
            }
        }
    }

    @Test
    fun `when main open up then should success`() {
        testCoroutineRule.runBlockingTest {
            //given

            //when
            viewModel.fetchWatchlist()

            //then
            verifyOrder {
                refreshTriggerObserver.onChanged(true)
                miniStockbitRepository.getWatchlist()
            }
        }
    }
}