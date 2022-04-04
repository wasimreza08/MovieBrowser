package com.example.featuremovie.presentation.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Movie(
    val id: Long,
    val imdbId: String,
    val overview: String,
    val title: String,
    val releaseDate: Date?,
    val voteAverage: Float?,
    val image: String
) : Parcelable
