package com.example.domainmovie.data.repository

import com.example.domainmovie.data.datasource.RemoteDataSource
import com.example.domainmovie.data.mapper.ResponseToDomainMapper
import com.example.domainmovie.domain.model.MovieDomainModel
import com.example.domainmovie.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    Repository {
    override fun getPopularMovies(): Flow<List<MovieDomainModel>> {
        val mapper = ResponseToDomainMapper()
        return remoteDataSource.getRemotePopularMovies().map { movies ->
            movies.map { mapper.map(it)}
        }
    }
}
