package com.example.domainmovie.data.api

import com.example.domainmovie.data.dto.MovieResponse
import com.example.domainmovie.data.dto.PaginatedListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBApi {
    @GET("movie/popular")
    suspend fun getPopularTvShows(
        @Query("language") query: String? = null,
        @Query("page") page: Int? = null
    ): PaginatedListResponse<MovieResponse>
}
