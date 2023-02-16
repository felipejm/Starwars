package com.felipe.starwars.features.category.list.presentation

import com.felipe.starwars.features.category.list.domain.Category

sealed class CategoriesCommand {
    data class OpenCategory(val category: Category) : CategoriesCommand()
}
