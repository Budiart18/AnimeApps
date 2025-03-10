package com.aeryz.animeapps.presentation.anime_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aeryz.core.data.source.remote.Resource
import com.aeryz.core.domain.model.Anime
import com.aeryz.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel (private val animeUseCase: AnimeUseCase): ViewModel() {

   private var _animeData = MutableStateFlow<Resource<Anime>?>(null)
   val animeData: StateFlow<Resource<Anime>?> = _animeData

   val animeFavourite = animeUseCase.getFavouriteAnime()

   val animeListInDb = animeUseCase.getAllAnime()

   fun getAnimeDetail(id: Int) {
      viewModelScope.launch {
         animeUseCase.getAnimeById(id).collect { resource ->
            _animeData.value = resource
         }
      }
   }

   fun setAnimeFavourite(anime: Anime, status: Boolean) {
      viewModelScope.launch(Dispatchers.IO) {
         animeUseCase.setFavouriteAnime(anime, status)
      }
   }

   fun insertFavoriteAnime(anime: Anime, status: Boolean) {
      viewModelScope.launch(Dispatchers.IO) {
         animeUseCase.insertFavouriteAnime(anime, status)
      }
   }

   companion object {
      const val TAG = "DetailViewModel"
   }
}