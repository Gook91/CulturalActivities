package com.gbl.culturalactivities.di

import android.content.Context
import com.gbl.culturalactivities.data.db.AppDatabase
import com.gbl.culturalactivities.data.db.CulturalActivitiesDao
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
    fun provideCulturalActivitiesDao(appDatabase: AppDatabase): CulturalActivitiesDao =
        appDatabase.culturalActivitiesDao()
}