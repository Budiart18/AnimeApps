package com.aeryz.core.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.aeryz.core.data.source.AnimeRepositoryImpl
import com.aeryz.core.data.source.local.LocalDataSource
import com.aeryz.core.data.source.local.room.AnimeDao
import com.aeryz.core.data.source.local.room.AnimeDatabase
import com.aeryz.core.data.source.remote.RemoteDataSource
import com.aeryz.core.data.source.remote.network.ApiService
import com.aeryz.core.domain.repository.AnimeRepository
import com.aeryz.core.domain.usecase.AnimeInteractor
import com.aeryz.core.domain.usecase.AnimeUseCase
import com.aeryz.core.utils.ApiConstant
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module {
   single { provideChuckerInterceptor(get()) }
   single { provideOkHttpClient(get()) }
   single { provideRetrofit(get()) }
   single { provideDatabase(get()) }
   single { provideDao(get()) }
   single { provideApiService(get()) }
   single { LocalDataSource(get()) }
   single { RemoteDataSource(get()) }
   single<AnimeRepository> { provideRepository(get(), get()) }
   single<AnimeUseCase> { provideAnimeInteractor(get()) }
}

private fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
   return ChuckerInterceptor.Builder(context).build()
}

private fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
   return OkHttpClient.Builder()
      .addInterceptor(chuckerInterceptor)
      .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
   return Retrofit.Builder()
      .baseUrl(ApiConstant.BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
}

private fun provideDatabase(context: Context): AnimeDatabase {
   return Room.databaseBuilder(
      context,
      AnimeDatabase::class.java,
      "anime.db"
   ).build()
}

private fun provideDao(animeDatabase: AnimeDatabase): AnimeDao {
   return animeDatabase.favouriteDao()
}

private fun provideApiService(retrofit: Retrofit): ApiService {
   return retrofit.create(ApiService::class.java)
}

private fun provideRepository(
   localDataSource: LocalDataSource,
   remoteDataSource: RemoteDataSource
): AnimeRepository {
   return AnimeRepositoryImpl(localDataSource, remoteDataSource)
}

private fun provideAnimeInteractor(animeRepository: AnimeRepository): AnimeUseCase {
   return AnimeInteractor(animeRepository)
}