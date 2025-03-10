package com.aeryz.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime(
   val id: Int?,
   val title: String?,
   val synopsis: String?,
   val images: String?,
   val rating: String?,
   val score: Double?,
   val duration: String?,
   val year: Int?,
   val isFavourite: Boolean?
): Parcelable