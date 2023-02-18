package com.felipe.starwars.features.category.detail.domain

import androidx.annotation.StringRes

data class CategoryDetail(
    val imageUrl: String,
    val title: String,
    @StringRes val firstAttributeLabel: Int,
    val firstAttribute: String,
    @StringRes val secondAttributeLabel: Int,
    val secondAttribute: String,
    @StringRes val thirdAttributeLabel: Int,
    val thirdAttribute: String,
    @StringRes val fourthAttributeLabel: Int,
    val fourthAttribute: String,
)
