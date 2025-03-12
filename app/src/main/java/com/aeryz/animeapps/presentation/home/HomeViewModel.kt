package com.aeryz.animeapps.presentation.home

import androidx.lifecycle.ViewModel
import com.aeryz.core.domain.usecase.AnimeUseCase

class HomeViewModel (animeUseCase: AnimeUseCase): ViewModel() {
   val animeList = animeUseCase.getAllAnime()
}