package com.example.domainmovie.domain.model

import java.util.Date

data class MovieDomainModel(
    val id: Long,
    val imdbId: String?,
    val overview: String?,
    val title: String,
    val releaseDate: Date?,
    val voteAverage: Float?,
    val image:String?
)
