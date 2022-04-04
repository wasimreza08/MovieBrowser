package com.example.domainmovie.domain.usecase

import com.example.corecommon.base.BaseUseCase
import com.example.domainmovie.domain.model.MovieDomainModel

interface GetPopularMovies : BaseUseCase<GetPopularMovies.Input, GetPopularMovies.Output> {
    object Input : BaseUseCase.Input

    sealed class Output : BaseUseCase.Output {
        data class Success(
            val movies: List<MovieDomainModel>
        ) : Output()

        object ErrorNetwork : Output()
        data class ErrorUnknown(val message: String?) : Output()
    }
}