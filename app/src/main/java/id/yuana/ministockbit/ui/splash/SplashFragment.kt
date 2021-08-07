package id.yuana.ministockbit.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.yuana.ministockbit.R

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.splash_fragment) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
    }

    private fun initObserver() {
        viewModel.next.observe(viewLifecycleOwner, Observer {
            when (it) {
                SplashViewModel.GOTO_LOGIN -> findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                SplashViewModel.GOTO_MAIN -> findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
        })
    }
}