package com.example.domainmovie.data.mapper

import com.example.corecommon.mapper.Mapper
import com.example.domainmovie.data.dto.MovieResponse
import com.example.domainmovie.domain.model.MovieDomainModel

class ResponseToDomainMapper : Mapper<MovieResponse, MovieDomainModel> {
    override fun map(from: MovieResponse): MovieDomainModel {
        return MovieDomainModel(
            id = from.id,
            imdbId = from.imdbId,
            overview = from.overview,
            title = from.title,
            releaseDate = from.releaseDate,
            voteAverage = from.voteAverage,
            image = from.poster
        )
    }
}