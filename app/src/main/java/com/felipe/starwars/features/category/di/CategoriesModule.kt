package com.felipe.starwars.features.category.di

import com.apollographql.apollo3.ApolloClient
import com.felipe.starwars.AppDatabase
import com.felipe.starwars.features.category.data.CategoriesRepository
import com.felipe.starwars.features.category.data.CategoriesRepositoryImpl
import com.felipe.starwars.features.category.data.CategoryApi
import com.felipe.starwars.features.category.data.CategoryDao
import com.felipe.starwars.features.category.detail.domain.CategoryDetailMapper
import com.felipe.starwars.features.category.detail.domain.CategoryDetailMapperImpl
import com.felipe.starwars.features.category.detail.domain.GetCategoryDetailUseCase
import com.felipe.starwars.features.category.detail.domain.GetCategoryDetailUseCaseImpl
import com.felipe.starwars.features.category.list.domain.CategoryMapper
import com.felipe.starwars.features.category.list.domain.CategoryMapperImpl
import com.felipe.starwars.features.category.list.domain.DeleteCategoryUseCase
import com.felipe.starwars.features.category.list.domain.DeleteCategoryUseCaseImpl
import com.felipe.starwars.features.category.list.domain.GetCategoriesUseCase
import com.felipe.starwars.features.category.list.domain.GetCategoriesUseCaseImpl
import com.felipe.starwars.features.category.list.domain.SaveCategoryUseCase
import com.felipe.starwars.features.category.list.domain.SaveCategoryUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CategoriesModule {

    @Provides
    fun provideCategoriesRepository(
        client: ApolloClient,
        api: CategoryApi,
        dao: CategoryDao
    ): CategoriesRepository = CategoriesRepositoryImpl(client, api, dao)

    @Provides
    fun provideGetCategoriesUseCase(
        repository: CategoriesRepository,
        mapper: CategoryMapper
    ): GetCategoriesUseCase = GetCategoriesUseCaseImpl(repository, mapper)

    @Provides
    fun provideSaveCategoryUseCase(
        repository: CategoriesRepository,
    ): SaveCategoryUseCase = SaveCategoryUseCaseImpl(repository)

    @Provides
    fun provideDeleteCategoryUseCase(
        repository: CategoriesRepository,
    ): DeleteCategoryUseCase = DeleteCategoryUseCaseImpl(repository)

    @Provides
    fun provideGetCategoryDetailUseCase(
        repository: CategoriesRepository,
        mapper: CategoryDetailMapper
    ): GetCategoryDetailUseCase = GetCategoryDetailUseCaseImpl(repository, mapper)

    @Provides
    fun provideCategoryMapper(): CategoryMapper = CategoryMapperImpl()

    @Provides
    fun provideCategoriesMapper(): CategoryDetailMapper = CategoryDetailMapperImpl()

    @Provides
    fun provideCategoryApi(
        retrofit: Retrofit
    ): CategoryApi = retrofit.create(CategoryApi::class.java)

    @Provides
    fun provideCategoryDao(
        appDataBase: AppDatabase
    ): CategoryDao = appDataBase.categoryDao()
}
