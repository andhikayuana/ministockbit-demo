package id.yuana.ministockbit.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import id.yuana.ministockbit.data.repository.AccountRepository
import id.yuana.ministockbit.util.CoroutinesTestRule
import io.mockk.MockKAnnotations
import io.mockk.every
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
class SplashViewModelTest {

    @get:Rule
    val testCoroutineRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SplashViewModel

    @MockK
    private lateinit var repository: AccountRepository

    @MockK(relaxed = true)
    private lateinit var nextObserver: Observer<Int>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        viewModel = SplashViewModel(repository)
        viewModel.next.observeForever(nextObserver)
    }

    @After
    fun tearDown() {
        viewModel.next.removeObserver(nextObserver)
    }

    @Test
    fun `when open splash screen and already logged on then should goto main`() {
        testCoroutineRule.runBlockingTest {
            //given
            every { repository.isLoggedOn() } returns true

            //when
            advanceTimeBy(1000)

            //then
            verifyOrder {
                nextObserver.onChanged(SplashViewModel.IDLE)
                nextObserver.onChanged(SplashViewModel.GOTO_MAIN)
            }

        }
    }

    @Test
    fun `when open splash and doesnt logged on then should goto login`() {
        testCoroutineRule.runBlockingTest {
            //given
            every { repository.isLoggedOn() } returns false

            //when
            advanceTimeBy(1000)

            //then
            verifyOrder {
                nextObserver.onChanged(SplashViewModel.IDLE)
                nextObserver.onChanged(SplashViewModel.GOTO_LOGIN)
            }
        }
    }
}