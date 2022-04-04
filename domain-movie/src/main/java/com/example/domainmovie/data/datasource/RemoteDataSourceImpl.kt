package com.example.domainmovie.data.datasource

import com.example.domainmovie.data.api.TMDBApi
import com.example.domainmovie.data.dto.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: TMDBApi) : RemoteDataSource {
    override fun getRemotePopularMovies(): Flow<List<MovieResponse>> = flow {
        emit(api.getPopularTvShows().results)
    }
}
