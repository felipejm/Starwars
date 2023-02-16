package com.felipe.starwars.features.category.list.domain

import com.felipe.starwars.BuildConfig
import java.util.*

interface CategoryMapper {
    fun parseCategories(favorites: List<Category>, response: Map<String, String>): List<Category>
}

class CategoryMapperImpl() : CategoryMapper {

    override fun parseCategories(favorites: List<Category>, response: Map<String, String>): List<Category> {
        return response.entries.map {
            Category(
                imageUrl = "${BuildConfig.IMAGE_BASE_URL}/categories/${it.key}.jpg",
                title = it.key.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                detailUrl = it.value,
                isFavorite = favorites.any { favorite -> favorite.title.equals(it.key, ignoreCase = true) }
            )
        }
    }
}
