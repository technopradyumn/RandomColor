package com.technopradyumn.randomcolor.data.repository

import com.google.firebase.database.DatabaseReference
import com.technopradyumn.randomcolor.data.db.ColorDao
import com.technopradyumn.randomcolor.data.db.ColorEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class ColorRepository(
    private val colorDao: ColorDao,
    private val firebaseDatabase: DatabaseReference
) {
    val allColors: Flow<List<ColorEntity>> = colorDao.getAllColors()

    suspend fun addColor(color: ColorEntity) = colorDao.insertColor(color)

    suspend fun syncColors(colors: List<ColorEntity>) {
        colors.forEach { color ->
            if (!color.isSynced) {
                firebaseDatabase.push().setValue(color)
                colorDao.updateSyncState(color.copy(isSynced = true))
            }
        }
    }

    suspend fun getUnsyncedCount(): Int {
        val unsyncedColors = colorDao.getAllColors().firstOrNull()?.count { !it.isSynced } ?: 0
        return unsyncedColors
    }
}