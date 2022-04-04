package com.example.domainmovie.domain.repository

import com.example.domainmovie.domain.model.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getPopularMovies(): Flow<List<MovieDomainModel>>
}