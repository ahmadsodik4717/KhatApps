package com.sodikdev.khatapps.data.repository

import android.net.Uri
import com.sodikdev.khatapps.data.model.History
import com.sodikdev.khatapps.data.model.Khat
import kotlinx.coroutines.flow.Flow
import java.io.File
import com.sodikdev.khatapps.util.Result

interface UserRepository {
    fun classification(image: File, imageUri: Uri): Flow<Result<List<Khat?>>>
    fun saveToHistory(image: Uri, disease: List<Khat?>): Flow<Result<String?>>
    fun getHistory(): Flow<Result<List<History>>>
    fun getDetailHistory(id: String): Flow<Result<History?>>
}