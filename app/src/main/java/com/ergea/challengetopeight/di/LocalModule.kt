package com.ergea.challengetopeight.di

import android.content.Context
import com.ergea.challengetopeight.data.local.database.AppDatabase
import com.ergea.challengetopeight.data.local.database.UserDao
import com.ergea.challengetopeight.data.local.datastore.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
// test
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getAppDB(context)

    @Singleton
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStoreManager =
        DataStoreManager(context)

}