package com.sodikdev.khatapps.data.source.firebase

import android.net.Uri
import com.sodikdev.khatapps.data.model.History
import com.sodikdev.khatapps.data.model.Khat

interface FirebaseService {
    suspend fun getDiseaseById(khatId: String): Khat?
    suspend fun saveToHistory(image: Uri, khats: List<Khat?>)
    suspend fun getHistories(): List<History>
    suspend fun getDetailHistory(id: String): History?
}