package com.aeryz.core.data.source.remote

import android.util.Log
import com.aeryz.core.data.source.remote.network.ApiResponse
import com.aeryz.core.data.source.remote.network.ApiService
import com.aeryz.core.data.source.remote.response.AnimeByIdResponse
import com.aeryz.core.data.source.remote.response.AnimeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService) {
   fun getAllAnime(): Flow<ApiResponse<List<AnimeResponse.Data>>> {
      return flow {
         val response = apiService.getAllAnime()
         val filteredData = response.data?.filterNotNull()
         if (!filteredData.isNullOrEmpty()) {
            Log.d("RemoteDataSource", "Check remote data: $filteredData")
            emit(ApiResponse.Success(filteredData))
         } else {
            emit(ApiResponse.Empty)
         }
      }
   }

   fun getAnimeById(id: Int): Flow<ApiResponse<AnimeByIdResponse>> {
      return flow {
         try {
            val response = apiService.getAnimeById(id)
            if (response.data != null) {
               emit(ApiResponse.Success(response))
            } else {
               emit(ApiResponse.Empty)
            }
         } catch (e: Exception) {
            emit(ApiResponse.Error(e.message ?: "Unknown error"))
         }
      }
   }

   fun searchAnime(query: String): Flow<ApiResponse<List<AnimeResponse.Data>>> {
      return flow {
         try {
            val response = apiService.searchAnime(query = query)
            val filteredData = response.data?.filterNotNull()
            if (!filteredData.isNullOrEmpty()) {
               emit(ApiResponse.Success(filteredData))
            } else {
               emit(ApiResponse.Empty)
            }
         } catch (e: Exception) {
            emit(ApiResponse.Error(e.message ?: "Unknown error"))
         }
      }
   }
}