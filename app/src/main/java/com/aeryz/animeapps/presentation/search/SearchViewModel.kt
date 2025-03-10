package com.aeryz.animeapps.presentation.search

import androidx.lifecycle.ViewModel
import com.aeryz.core.data.source.remote.Resource
import com.aeryz.core.domain.model.Anime
import com.aeryz.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.flow.Flow

class SearchViewModel (private val animeUseCase: AnimeUseCase): ViewModel() {

   fun searchAnime(query: String): Flow<Resource<List<Anime>>> {
      return animeUseCase.searchAnime(query)
   }
}