package id.yuana.ministockbit.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.yuana.ministockbit.R
import id.yuana.ministockbit.util.makeLinks
import kotlinx.android.synthetic.main.login_fragment.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment) {

    val viewModel: LoginViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        btnLogin.setOnClickListener {
//            viewModel.onLoginClicked()
            findNavController().navigate(R.id.action_loginFragment2_to_mainFragment)
        }

        tvForgotPassword.makeLinks(
            Pair(
                getString(R.string.label_forgot_password),
                View.OnClickListener {
                    Log.d("YUANA", "FORGOT PASSWORD")
                })
        )
        tvRegisterNow.makeLinks(Pair(getString(R.string.label_register_now), View.OnClickListener {
            Log.d("YUANA", "REGISTER NOW")
        }))
    }
}