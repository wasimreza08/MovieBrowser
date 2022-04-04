package com.example.featuremovie.presentation.viewmodel

import com.example.corecommon.base.ViewEffect
import com.example.corecommon.base.ViewEvent
import com.example.corecommon.base.ViewState
import com.example.featuremovie.presentation.uimodel.Movie

object MovieContract {
    data class State(
        val movies: List<Movie> = emptyList(),
        val isLoading: Boolean = false
    ) : ViewState

    sealed class Event : ViewEvent {
        object OnRequestData : Event()
    }

    sealed class Effect : ViewEffect {
        object NetworkErrorEffect : Effect()
        data class UnknownErrorEffect(val message: String) : Effect()
    }
}
