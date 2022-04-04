package com.example.featuremovie.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.corecommon.ext.asVerticalLayout
import com.example.corecommon.ext.doNotLeak
import com.example.corecommon.ext.exhaustive
import com.example.corecommon.ext.viewBinding
import com.example.featuremovie.R
import com.example.featuremovie.databinding.ActivityListBinding
import com.example.featuremovie.presentation.uimodel.Movie
import com.example.featuremovie.presentation.view.adapter.MovieListAdapter
import com.example.featuremovie.presentation.viewmodel.MovieContract
import com.example.featuremovie.presentation.viewmodel.MoviesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private val viewModel: MoviesViewModel by viewModels()
    private val binding by viewBinding(ActivityListBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupAdapter()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.onUiEvent(MovieContract.Event.OnRequestData)
        }
        viewStateObserver()
        viewEffectObserver()
    }

    private fun viewEffectObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effect.collect { effect ->
                    when (effect) {
                        is MovieContract.Effect.NetworkErrorEffect -> {
                            showSnackBar(getString(R.string.network_error))
                        }
                        is MovieContract.Effect.UnknownErrorEffect -> {
                            showSnackBar(getString(R.string.unknown_error))
                        }
                    }.exhaustive
                }
            }
        }
    }

    private fun viewStateObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { viewState ->
                    updateMovies(viewState.movies)
                    setLoading(viewState.isLoading)
                }
            }
        }
    }

    private fun showSnackBar(text: String) {
        Snackbar.make(binding.swipeRefresh, text, Snackbar.LENGTH_LONG).show()
    }

    private fun setupAdapter() {
        binding.list.run {
            doNotLeak(this@ListActivity)
            asVerticalLayout()
            adapter = MovieListAdapter()
        }
    }

    private fun updateMovies(movies: List<Movie>) {
        (binding.list.adapter as? MovieListAdapter)?.apply {
            items = movies
        }
    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading && binding.swipeRefresh.isRefreshing) return
        binding.swipeRefresh.isRefreshing = isLoading
    }
}
