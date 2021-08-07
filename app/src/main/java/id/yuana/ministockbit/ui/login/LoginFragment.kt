package id.yuana.ministockbit.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import id.yuana.ministockbit.R
import id.yuana.ministockbit.util.Resource
import id.yuana.ministockbit.util.makeLinks
import kotlinx.android.synthetic.main.login_fragment.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment) {

    private val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    btnLogin.text = getString(R.string.label_loading)
                    toggleComponent(false)
                }
                Resource.Status.SUCCESS -> {
                    btnLogin.text = getString(R.string.label_login)
                    toggleComponent(true)
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }
                Resource.Status.ERROR -> {
                    btnLogin.text = getString(R.string.label_login)
                    toggleComponent(true)
                    Snackbar.make(coordinatorLogin, it.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

        })
    }

    private fun toggleComponent(enabled: Boolean) {
        btnLoginGoogle.isEnabled = enabled
        btnLoginFacebook.isEnabled = enabled
        btnLoginFingerprint.isEnabled = enabled
        tvForgotPassword.isEnabled = enabled
        tvRegisterNow.isEnabled = enabled
        btnLogin.isEnabled = enabled
        tieEmail.isEnabled = enabled
        tiePassword.isEnabled = enabled
    }

    private fun initView() {
        btnLogin.setOnClickListener {
            viewModel.onLoginClicked(
                email = tieEmail.text.toString().trim(),
                password = tiePassword.text.toString().trim()
            )

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