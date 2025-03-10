package com.aeryz.core.utils

import com.aeryz.core.data.source.local.entity.AnimeEntity
import com.aeryz.core.data.source.remote.response.AnimeByIdResponse
import com.aeryz.core.data.source.remote.response.AnimeResponse
import com.aeryz.core.domain.model.Anime
import java.util.ArrayList

object DataMapper {
   fun mapResponseToEntities(input: List<AnimeResponse.Data>): List<AnimeEntity> {
      val animeList = ArrayList<AnimeEntity>()
      input.map {
         val anime = AnimeEntity(
            id = it.malId,
            title = it.title,
            synopsis = it.synopsis,
            images = it.images?.jpg?.imageUrl,
            rating = it.rating,
            score = it.score,
            duration = it.duration,
            year = it.year,
            isFavourite = false
         )
         animeList.add(anime)
      }
      return animeList
   }

   fun mapResponseByIdToDomain(input: AnimeByIdResponse): Anime {
      return Anime(
         id = input.data?.malId,
         title = input.data?.title,
         synopsis = input.data?.synopsis,
         images = input.data?.images?.jpg?.imageUrl,
         rating = input.data?.rating,
         score = input.data?.score,
         duration = input.data?.duration,
         year = input.data?.year,
         isFavourite = false
      )
   }

   fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Anime> {
      val animeList = ArrayList<Anime>()
      input.map {
         val anime = Anime(
            id = it.id,
            title = it.title,
            synopsis = it.synopsis,
            images = it.images,
            rating = it.rating,
            score = it.score,
            duration = it.duration,
            year = it.year,
            isFavourite = false
         )
         animeList.add(anime)
      }
      return animeList
   }

   fun mapDomainToEntities(input: Anime): AnimeEntity{
      return AnimeEntity(
         id = input.id,
         title = input.title,
         synopsis = input.synopsis,
         images = input.images,
         rating = input.rating,
         score = input.score,
         duration = input.duration,
         year = input.year,
         isFavourite = false)
   }

   fun mapResponseSearchToDomain(input: List<AnimeResponse.Data>): List<Anime> {
      val animeList = ArrayList<Anime>()
      input.map {
         val anime = Anime(
            id = it.malId,
            title = it.title,
            synopsis = it.synopsis,
            images = it.images?.jpg?.imageUrl,
            rating = it.rating,
            score = it.score,
            duration = it.duration,
            year = it.year,
            isFavourite = false
         )
         animeList.add(anime)
      }
      return animeList
   }
}