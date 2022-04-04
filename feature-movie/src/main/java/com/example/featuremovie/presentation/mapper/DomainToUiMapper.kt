package com.example.featuremovie.presentation.mapper

import com.example.corecommon.mapper.Mapper
import com.example.domainmovie.domain.model.MovieDomainModel
import com.example.featuremovie.presentation.uimodel.Movie
import com.example.featuremovie.presentation.util.Constants

class DomainToUiMapper : Mapper<MovieDomainModel, Movie> {
    override fun map(from: MovieDomainModel): Movie {
        return Movie(
            id = from.id,
            imdbId = from.imdbId.orEmpty(),
            overview = from.overview.orEmpty(),
            title = from.title,
            releaseDate = from.releaseDate,
            voteAverage = from.voteAverage,
            image = from.image?.let {
                Constants.IMAGE_PATH_PREFIX + it
            }.orEmpty()
        )
    }
}