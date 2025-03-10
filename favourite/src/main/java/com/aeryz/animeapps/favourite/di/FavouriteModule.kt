package com.aeryz.animeapps.favourite.di

import com.aeryz.animeapps.favourite.presentation.FavouriteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module {
   viewModel { FavouriteViewModel(get()) }
}