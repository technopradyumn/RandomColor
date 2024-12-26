package com.technopradyumn.randomcolor.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
    @Query("SELECT * FROM colors")
    fun getAllColors(): Flow<List<ColorEntity>>

    @Insert
    suspend fun insertColor(color: ColorEntity)

    @Update
    suspend fun updateSyncState(color: ColorEntity)
}