package com.technopradyumn.randomcolor.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ColorEntity::class], version = 1, exportSchema = false)
abstract class ColorDatabase : RoomDatabase() {
    abstract fun colorDao(): ColorDao
}