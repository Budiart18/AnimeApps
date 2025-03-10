package com.aeryz.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aeryz.core.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
   @Insert
   fun insertAnimeList(anime: List<AnimeEntity>)

   @Insert
   fun insertFavoriteAnime(anime: AnimeEntity)

   @Update
   fun updateFavouriteAnime(anime: AnimeEntity)

   @Query("SELECT * FROM anime")
   fun getAllAnime(): Flow<List<AnimeEntity>>

   @Query("SELECT * FROM anime WHERE isFavourite = 1")
   fun getFavouriteAnime(): Flow<List<AnimeEntity>>

   @Query("SELECT * FROM anime WHERE id = :id LIMIT 1")
   fun getAnimeById(id: Int): Flow<AnimeEntity>
}