package com.santoshpillai.projectone.di

import android.content.Context
import com.santoshpillai.projectone.data.local.AppDatabase
import com.santoshpillai.projectone.data.local.StudentDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideStudentDao(appDatabase: AppDatabase): StudentDAO {
        return appDatabase.studentDAO()
    }
}