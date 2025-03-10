package com.aeryz.animeapps.di

import com.aeryz.animeapps.presentation.anime_detail.AnimeDetailViewModel
import com.aeryz.animeapps.presentation.search.SearchViewModel
import com.aeryz.animeapps.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var appModule = module {
   viewModel { HomeViewModel(get()) }
   viewModel { AnimeDetailViewModel(get()) }
   viewModel { SearchViewModel(get()) }
}