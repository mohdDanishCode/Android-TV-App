package com.network.core.di

import android.content.Context
import androidx.room.Room
import com.network.core.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            com.network.core.BuildConfig.DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()
    }



    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideProgressDao(db: AppDatabase) = db.watchProgressDao()

    @Singleton
    @Provides
    fun provideRailDao(db: AppDatabase) = db.movieRailDao()

}
