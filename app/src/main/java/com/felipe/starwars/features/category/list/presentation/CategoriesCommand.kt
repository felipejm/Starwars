package com.felipe.starwars.features.category.list.presentation

sealed class CategoriesCommand {
    data class OpenCategory(val category: String) : CategoriesCommand()
}
