package com.example.domainmovie.data.datasource

import com.example.domainmovie.data.dto.MovieResponse
import kotlinx.coroutines.flow.Flow


interface RemoteDataSource {
    fun getRemotePopularMovies(): Flow<List<MovieResponse>>
}
