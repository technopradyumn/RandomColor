package com.technopradyumn.randomcolor.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.technopradyumn.randomcolor.data.db.ColorDao
import com.technopradyumn.randomcolor.data.db.ColorDatabase
import com.technopradyumn.randomcolor.data.repository.ColorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ColorDatabase =
        Room.databaseBuilder(context, ColorDatabase::class.java, "color_db").build()

    @Provides
    fun provideColorDao(database: ColorDatabase): ColorDao = database.colorDao()

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("colors")

    @Provides
    @Singleton
    fun provideRepository(
        colorDao: ColorDao,
        firebaseDatabase: DatabaseReference
    ): ColorRepository = ColorRepository(colorDao, firebaseDatabase)
}