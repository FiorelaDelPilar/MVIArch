package com.example.mviarch.favouriteModule.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mviarch.commonModule.utils.Constants
import com.example.mviarch.R
import com.example.mviarch.WineApplication
import com.example.mviarch.accountModule.model.AccountState
import com.example.mviarch.updateModule.UpdateDialogFragment
import com.example.mviarch.commonModule.entities.Wine
import com.example.mviarch.commonModule.utils.OnClickListener
import com.example.mviarch.commonModule.view.WineBaseFragment
import com.example.mviarch.favouriteModule.FavouriteViewModel
import com.example.mviarch.favouriteModule.FavouriteViewModelFactory
import com.example.mviarch.favouriteModule.intent.FavouriteIntent
import com.example.mviarch.favouriteModule.model.FavouriteRepository
import com.example.mviarch.favouriteModule.model.FavouriteState
import com.example.mviarch.favouriteModule.model.RoomDatabase
import com.example.mviarch.mainModule.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/****
 * Project: Wines
 * From: com.cursosant.wines
 * Created by Alain Nicolás Tello on 06/02/24 at 20:23
 * All rights reserved 2024.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
class FavouriteFragment : WineBaseFragment(), OnClickListener {

    private lateinit var adapter: WineFavListAdapter
    private lateinit var vm: FavouriteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupAdapter()
        setupRecyclerView()
        setupSwipeRefresh()
        setupObservers()
    }

    private fun setupViewModel() {
        vm = ViewModelProvider(
            this,
            FavouriteViewModelFactory(FavouriteRepository(RoomDatabase()))
        )[FavouriteViewModel::class.java]

    }

    private fun setupAdapter() {
        adapter = WineFavListAdapter()
        adapter.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = this@FavouriteFragment.adapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.srlResults.setOnRefreshListener {
            getWines()
        }
    }

    private fun getWines() {
        lifecycleScope.launch(Dispatchers.IO) {
            vm.channel.send(FavouriteIntent.RequestWine)
            /*
              try {
                val wines = WineApplication.database.wineDao().getAllWines()
                withContext(Dispatchers.Main) {
                    if (wines.isNotEmpty()) {
                        showNoDataView(false)
                        showRecyclerView(true)
                        adapter.submitList(wines)
                    } else {
                        showRecyclerView(false)
                        showNoDataView(true)
                    }
                }
            } catch (e: Exception) {
                showMsg(R.string.room_request_fail)
            } finally {
                showProgress(false)
            }
             */
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {

            vm.state.collect { state ->
                when (state) {
                    is FavouriteState.Init -> {}
                    is FavouriteState.ShowProgress -> showProgress(true)
                    is FavouriteState.HideProgress -> showProgress(false)
                    is FavouriteState.RequestWineSuccess -> adapter.submitList(state.list)
                    is FavouriteState.AddWineSucess -> showMsg(state.msgRes)
                    is FavouriteState.DeleteWineSucess -> showMsg(state.msgRes)
                    is FavouriteState.Fail -> showMsg(state.msgRes)

                }
            }
        }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()
    }

    private fun showProgress(isVisible: Boolean) {
        binding.srlResults.isRefreshing = isVisible
    }

    private fun showNoDataView(isVisible: Boolean) {
        binding.tvNoData.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun showRecyclerView(isVisible: Boolean) {
        binding.recyclerView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun onResume() {
        super.onResume()
        getWines()
    }

    /*
    * OnClickListener
    * */
    override fun onFavorite(wine: Wine) {
        wine.isFavorite = !wine.isFavorite
        lifecycleScope.launch(Dispatchers.IO) {
            if (wine.isFavorite) {
                vm.channel.send(FavouriteIntent.AddWine(wine))
                /*
                val result = WineApplication.database.wineDao().addWine(wine)
                 if (result == -1L) {
                     Snackbar.make(binding.root, R.string.room_save_fail, Snackbar.LENGTH_SHORT)
                         .show()
                 } else {
                     Snackbar.make(binding.root, R.string.room_save_success, Snackbar.LENGTH_SHORT)
                         .show()
                 }
                 */
            } else {
                vm.channel.send(FavouriteIntent.DeleteWine(wine))
                val result = WineApplication.database.wineDao().deleteWine(wine)
                /*
                if (result == 0) {
                    Snackbar.make(binding.root, R.string.room_save_fail, Snackbar.LENGTH_SHORT)
                        .show()
                } else {
                    Snackbar.make(binding.root, R.string.room_save_success, Snackbar.LENGTH_SHORT)
                        .show()
                }
                 */
            }
        }
    }

    override fun onLongClick(wine: Wine) {
        val fragmentManager = childFragmentManager
        val fragment = UpdateDialogFragment()
        val args = Bundle()
        args.putDouble(Constants.ARG_ID, wine.id)
        fragment.arguments = args
        fragment.show(fragmentManager, UpdateDialogFragment::class.java.simpleName)
        fragment.setOnUpdateListener {
            binding.srlResults.isRefreshing = true
            getWines()
        }
    }
}