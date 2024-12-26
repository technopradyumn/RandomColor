package com.technopradyumn.randomcolor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technopradyumn.randomcolor.data.db.ColorEntity
import com.technopradyumn.randomcolor.data.repository.ColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(private val repository: ColorRepository) : ViewModel() {

    val colors: Flow<List<ColorEntity>> = repository.allColors

    private val _pendingCount = MutableStateFlow(0)
    val pendingCount = _pendingCount.asStateFlow()

    fun addRandomColor() {
        viewModelScope.launch {
            val randomColor = String.format("#%06X", Random().nextInt(0xFFFFFF))
            val time = System.currentTimeMillis()
            repository.addColor(ColorEntity(color = randomColor, time = time))
            updatePendingCount()
        }
    }

    fun syncColors() {
        viewModelScope.launch {
            val colorsToSync = repository.allColors.firstOrNull()?.filter { !it.isSynced } ?: emptyList()
            if (colorsToSync.isNotEmpty()) {
                repository.syncColors(colorsToSync)
                updatePendingCount()
            }
        }
    }

    fun updatePendingCount() {
        viewModelScope.launch {
            val unsyncedCount = repository.getUnsyncedCount()
            _pendingCount.value = unsyncedCount
        }
    }
}