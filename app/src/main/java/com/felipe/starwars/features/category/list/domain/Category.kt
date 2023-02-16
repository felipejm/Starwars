package com.felipe.starwars.features.category.list.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "category")
data class Category(
    val imageUrl: String,
    @PrimaryKey
    val title: String,
    val detailUrl: String,
    val isFavorite: Boolean
) : Parcelable
