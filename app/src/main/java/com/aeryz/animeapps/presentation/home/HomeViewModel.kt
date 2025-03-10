package com.aeryz.animeapps.presentation.home

import androidx.lifecycle.ViewModel
import com.aeryz.core.domain.usecase.AnimeUseCase

class HomeViewModel (private val animeUseCase: AnimeUseCase): ViewModel() {
   val animeList = animeUseCase.getAllAnime()
}