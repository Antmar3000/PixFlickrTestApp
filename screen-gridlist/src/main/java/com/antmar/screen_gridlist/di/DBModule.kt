package com.antmar.screen_gridlist.di

import android.content.Context
import androidx.room.Room
import com.antmar.screen_gridlist.data.room.PictureDao
import com.antmar.screen_gridlist.data.room.PictureDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : PictureDatabase {
        return Room.databaseBuilder(
            context, PictureDatabase::class.java,
            "database.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao (database: PictureDatabase) : PictureDao {
        return database.getPictureDao()
    }
}