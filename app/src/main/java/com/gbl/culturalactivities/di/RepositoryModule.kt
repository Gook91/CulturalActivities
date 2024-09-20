package com.gbl.culturalactivities.di

import com.gbl.culturalactivities.data.CulturalActivityRepositoryImpl
import com.gbl.culturalactivities.domain.repository.CulturalActivityRepository
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
}