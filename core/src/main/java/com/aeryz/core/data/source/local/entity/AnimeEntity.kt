package com.aeryz.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
   @PrimaryKey
   val id: Int?,
   val title: String?,
   val synopsis: String?,
   val images: String?,
   val rating: String?,
   val score: Double?,
   val duration: String?,
   val year: Int?,
   var isFavourite: Boolean = false
)
