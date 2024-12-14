package com.gbl.culturalactivities.di

import android.content.Context
import com.gbl.culturalactivities.data.db.AppDatabase
import com.gbl.culturalactivities.data.db.CulturalActivityDao
import com.gbl.culturalactivities.data.db.PlaceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    fun provideCulturalActivityDao(appDatabase: AppDatabase): CulturalActivityDao =
        appDatabase.culturalActivityDao()

    @Provides
    fun providePlaceDao(appDatabase: AppDatabase): PlaceDao =
        appDatabase.placeDao()
}