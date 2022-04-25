package me.ruyeo.mvvmb12.ui.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import me.ruyeo.mvvmb12.R
import me.ruyeo.mvvmb12.data.api.ApiClient
import me.ruyeo.mvvmb12.data.api.ApiService
import me.ruyeo.mvvmb12.repository.PostRepository
import me.ruyeo.mvvmb12.repository.factory.PostViewModelFactory
import me.ruyeo.mvvmb12.utils.UiStateList


class PostFragment : Fragment(R.layout.fragment_post) {

    private lateinit var viewModel: PostViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupObservers()
        viewModel.getPosts()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.postState.collect{
                when(it){
                    is UiStateList.LOADING -> {
                        //show progress
                    }
                    is UiStateList.SUCCESS -> {
                        Log.d("Tag",it.data.toString())
                    }
                    is UiStateList.ERROR -> {
                        Log.d("Tag",it.message)
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            PostViewModelFactory(PostRepository(ApiClient.createService(ApiService::class.java)))
        )[PostViewModel::class.java]
    }
}
