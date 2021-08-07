package id.yuana.ministockbit.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.yuana.ministockbit.R
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.hasLogout.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
            }
        })
    }

    private fun initView() {
        toolbarTop.setNavigationOnClickListener {
            drawerLayout.open()
        }
        navigationView.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.menuLogout -> viewModel.logout()
            }

            drawerLayout.close()
            true
        }
        val badgeSearch = bottomNavigation.getOrCreateBadge(R.id.menuActionSearch)
        badgeSearch.apply {
            isVisible = true
            backgroundColor = ContextCompat.getColor(requireContext(), R.color.green_600)
        }
    }

}