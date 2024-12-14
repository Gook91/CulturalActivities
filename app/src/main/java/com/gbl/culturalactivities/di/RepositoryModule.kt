package com.gbl.culturalactivities.di

import com.gbl.culturalactivities.data.CulturalActivityRepositoryImpl
import com.gbl.culturalactivities.data.PlaceRepositoryImpl
import com.gbl.culturalactivities.domain.repository.CulturalActivityRepository
import com.gbl.culturalactivities.domain.repository.PlaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideCulturalActivitiesRepository(
        culturalActivityRepositoryImpl: CulturalActivityRepositoryImpl
    ): CulturalActivityRepository = culturalActivityRepositoryImpl

    @Provides
    fun providePlaceRepository(
        placeRepositoryImpl: PlaceRepositoryImpl
    ): PlaceRepository = placeRepositoryImpl
}