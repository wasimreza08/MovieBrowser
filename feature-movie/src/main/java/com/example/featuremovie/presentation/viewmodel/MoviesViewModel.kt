package com.example.featuremovie.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.corecommon.base.BaseViewModel
import com.example.corecommon.ext.exhaustive
import com.example.domainmovie.domain.usecase.GetPopularMovies
import com.example.featuremovie.presentation.mapper.DomainToUiMapper
import com.example.featuremovie.presentation.viewmodel.MovieContract.Effect
import com.example.featuremovie.presentation.viewmodel.MovieContract.Event
import com.example.featuremovie.presentation.viewmodel.MovieContract.Event.OnRequestData
import com.example.featuremovie.presentation.viewmodel.MovieContract.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMovies: GetPopularMovies
) : BaseViewModel<Event, State, Effect>() {

    init {
        onUiEvent(OnRequestData)
    }

    private fun fetchMovies() {
        updateState { copy(isLoading = true) }
        viewModelScope.launch {
            fetchPopularMovies()
        }
    }

    private suspend fun fetchPopularMovies() {
        getPopularMovies.execute(GetPopularMovies.Input).collect { output ->
            when (output) {
                is GetPopularMovies.Output.Success -> {
                    val mapper = DomainToUiMapper()
                    val movies = output.movies.map { mapper.map(it) }
                    updateState { copy(movies = movies) }
                }
                is GetPopularMovies.Output.ErrorNetwork -> {
                    sendEffect { Effect.NetworkErrorEffect }
                }
                is GetPopularMovies.Output.ErrorUnknown -> {
                    sendEffect { Effect.UnknownErrorEffect(output.message.orEmpty()) }
                }
            }.exhaustive
            updateState { copy(isLoading = false) }
        }
    }

    override fun provideInitialState() = State()

    override fun handleEvent(event: Event) {
        when (event) {
            is OnRequestData -> fetchMovies()
        }.exhaustive
    }
}
