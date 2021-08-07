package id.yuana.ministockbit.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.yuana.ministockbit.R
import id.yuana.ministockbit.ui.main.watchlist.WatchlistAdapter
import id.yuana.ministockbit.util.Resource
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var watchlistAdapter: WatchlistAdapter

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
        viewModel.watchlistState.observe(viewLifecycleOwner, Observer { res ->
            when (res.status) {
                Resource.Status.LOADING -> {
                    swipeRefresh.isRefreshing = true
                }
                Resource.Status.ERROR -> {
                    swipeRefresh.isRefreshing = false
                    //todo error state here
                }
                Resource.Status.SUCCESS -> {
                    swipeRefresh.isRefreshing = false
                    res.data?.let { watchlistAdapter.addOrUpdate(it) }
                }
                else -> {
                    //do nothing
                }
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
        swipeRefresh.setOnRefreshListener { viewModel.fetchWatchlist() }
        swipeRefresh.setColorSchemeResources(R.color.green_600)

        val badgeSearch = bottomNavigation.getOrCreateBadge(R.id.menuActionSearch)
        badgeSearch.apply {
            isVisible = true
            backgroundColor = ContextCompat.getColor(requireContext(), R.color.green_600)
        }
        watchlistAdapter = WatchlistAdapter()
        recyclerWatchlist.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = watchlistAdapter
        }
    }

}