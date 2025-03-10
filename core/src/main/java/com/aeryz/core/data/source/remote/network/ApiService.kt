package com.aeryz.core.data.source.remote.network

import com.aeryz.core.data.source.remote.response.AnimeByIdResponse
import com.aeryz.core.data.source.remote.response.AnimeResponse
import com.aeryz.core.utils.ApiConstant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
   @GET(ApiConstant.GET_ALL_ANIME)
   suspend fun getAllAnime(
      @Query("type") type: String = "movie"
   ): AnimeResponse

   @GET(ApiConstant.GET_ANIME_ID)
   suspend fun getAnimeById(
      @Path("id") id: Int
   ): AnimeByIdResponse

   @GET(ApiConstant.GET_ALL_ANIME)
   suspend fun searchAnime(
      @Query("type") type: String = "movie",
      @Query("q") query: String
   ): AnimeResponse
}