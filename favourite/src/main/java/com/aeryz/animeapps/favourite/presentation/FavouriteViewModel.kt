package com.aeryz.animeapps.favourite.presentation

import androidx.lifecycle.ViewModel
import com.aeryz.core.domain.usecase.AnimeUseCase

class FavouriteViewModel (animeUseCase: AnimeUseCase): ViewModel() {
   val favouriteAnime = animeUseCase.getFavouriteAnime()
}