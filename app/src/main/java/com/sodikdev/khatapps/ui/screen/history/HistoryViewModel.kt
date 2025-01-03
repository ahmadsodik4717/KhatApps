/*
package com.sodikdev.khatapps.ui.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sodikdev.khatapps.data.model.History
import com.sodikdev.khatapps.util.DetectResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.net.Uri
import android.util.Log
import com.sodikdev.khatapps.data.model.Khat
import com.sodikdev.khatapps.data.source.firebase.FirebaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val firebaseService: FirebaseService
) : ViewModel() {
    private val _historyItems = MutableStateFlow<List<History>>(emptyList())
    val historyItems: StateFlow<List<History>> = _historyItems.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            try {
                val histories = firebaseService.getHistories()
                _historyItems.value = histories
            } catch (e: Exception) {
                Log.d("HistoryViewModel", "Error loading history: ${e.message}")
            }
        }
    }

    fun addToHistory(detectResult: DetectResult, imageUri: String) {
        viewModelScope.launch {
            val historyItem = History(
                name = detectResult.diseaseName,
                author = detectResult.author,
                description = detectResult.description,
                confidence = detectResult.confidencePercentage,
                images = imageUri,
                timestamp = System.currentTimeMillis()
            )

            val khat = Khat(
                name = historyItem.name,
                author = historyItem.author,
                description = historyItem.description,
                confidence = historyItem.confidence.toDouble()
            )

            _historyItems.value = _historyItems.value + historyItem
            firebaseService.saveToHistory(Uri.parse(imageUri), listOf(khat))
        }
    }

    fun clearHistory() {
        viewModelScope.launch {
            _historyItems.value = emptyList()
        }
    }
}
*/
