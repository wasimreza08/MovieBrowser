package com.example.domainmovie.domain.usecase

import com.example.corecommon.dispatcher.BaseDispatcherProvider
import com.example.corecommon.ext.isNetworkException
import com.example.domainmovie.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPopularMoviesImpl @Inject constructor(
    private val repository: Repository,
    private val dispatcherProvider: BaseDispatcherProvider
) : GetPopularMovies {
    override fun execute(input: GetPopularMovies.Input): Flow<GetPopularMovies.Output> =
        repository.getPopularMovies().map { shows ->
            GetPopularMovies.Output.Success(shows) as GetPopularMovies.Output
        }.catch { exception ->
            if (exception.isNetworkException()) {
                emit(GetPopularMovies.Output.ErrorNetwork)
            } else {
                emit(GetPopularMovies.Output.ErrorUnknown(exception.message))
            }
        }.flowOn(dispatcherProvider.io())
}
