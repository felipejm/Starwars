package com.felipe.starwars.features.category.detail.domain

import com.felipe.starwars.BuildConfig
import com.felipe.starwars.GetAllFilmsQuery
import com.felipe.starwars.GetAllPeopleQuery
import com.felipe.starwars.GetAllPlanetsQuery
import com.felipe.starwars.GetAllVehiclesQuery

interface CategoryDetailMapper {
    suspend fun parseFilms(category: GetAllFilmsQuery.Data): List<CategoryDetail>
    suspend fun parsePeople(response: GetAllPeopleQuery.Data): List<CategoryDetail>
    suspend fun parseVehicles(response: GetAllVehiclesQuery.Data): List<CategoryDetail>
    suspend fun parsePlanets(response: GetAllPlanetsQuery.Data): List<CategoryDetail>
}

class CategoryDetailMapperImpl() : CategoryDetailMapper {

    override suspend fun parseFilms(response: GetAllFilmsQuery.Data): List<CategoryDetail> {
        return response.allFilms?.films?.map {
            CategoryDetail(
                imageUrl = "${BuildConfig.IMAGE_BASE_URL}/films/${it?.id}.jpg",
                title = it?.title.orEmpty(),
                firstAttribute = it?.releaseDate.orEmpty(),
                secondAttribute = it?.created.toString(),
                thirdAttribute = it?.producers?.joinToString { "," }.toString()
            )
        }.orEmpty()
    }

    override suspend fun parsePeople(response: GetAllPeopleQuery.Data): List<CategoryDetail> {
        return response.allPeople?.people?.map {
            CategoryDetail(
                imageUrl = "${BuildConfig.IMAGE_BASE_URL}/characters/${it?.id}.jpg",
                title = it?.name.orEmpty(),
                firstAttribute = it?.gender.orEmpty(),
                secondAttribute = it?.height.toString(),
                thirdAttribute = it?.hairColor.toString()
            )
        }.orEmpty()
    }

    override suspend fun parseVehicles(response: GetAllVehiclesQuery.Data): List<CategoryDetail> {
        return response.allVehicles?.vehicles?.map {
            CategoryDetail(
                imageUrl = "${BuildConfig.IMAGE_BASE_URL}/vehicles/${it?.id}.jpg",
                title = it?.name.orEmpty(),
                firstAttribute = it?.model.orEmpty(),
                secondAttribute = it?.passengers.toString(),
                thirdAttribute = it?.maxAtmospheringSpeed.toString()
            )
        }.orEmpty()
    }

    override suspend fun parsePlanets(response: GetAllPlanetsQuery.Data): List<CategoryDetail> {
        return response.allPlanets?.planets?.map {
            CategoryDetail(
                imageUrl = "${BuildConfig.IMAGE_BASE_URL}/planets/${it?.id}.jpg",
                title = it?.name.orEmpty(),
                firstAttribute = it?.diameter.toString(),
                secondAttribute = it?.gravity.toString(),
                thirdAttribute = it?.edited.toString()
            )
        }.orEmpty()
    }
}
