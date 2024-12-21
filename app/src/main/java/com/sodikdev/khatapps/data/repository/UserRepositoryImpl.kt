package com.sodikdev.khatapps.data.repository

import android.net.Uri
import com.sodikdev.khatapps.data.model.Khat
import com.sodikdev.khatapps.data.source.firebase.FirebaseService
import com.sodikdev.khatapps.data.source.remote.retrofit.ApiService
import com.sodikdev.khatapps.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val firebaseService: FirebaseService
) : UserRepository {

    override fun classification(image: File, imageUri: Uri) = flow {
        emit(
            Result.Loading(
                message = "Sedang mengunggah gambar",
                title = "1/2"
            )
        )

        try {
            val requestImageFile = image.asRequestBody("image/jpg".toMediaTypeOrNull())
            val imageMultipart = MultipartBody.Part.createFormData(
                "file",
                image.name,
                requestImageFile
            )

            val result = apiService.scanKhat(imageMultipart)

            emit(
                Result.Loading(
                    message = "Harap Tunggu, Deteksi Sedang Berlangsung...",
                    title = "2/2"
                )
            )

            val classification = result.predictions
                .filter { (it.confidence) >= 0.1 }
                .map { item ->
                    firebaseService.getDiseaseById(item.classDetect.lowercase())?.copy(
                        confidence = item.confidence
                    )
                }

            emit(
                Result.Loading(
                    message = "Menyimpan Riwayat",
                    title = "3/3"
                )
            )

            firebaseService.saveToHistory(imageUri, classification)

            if (classification.isEmpty()) {
                emit(Result.Error("Gambar tidak terklasifikasi"))
            }

            emit(Result.Success(classification))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun saveToHistory(image: Uri, khat: List<Khat?>) = flow {
        emit(Result.Loading())

        try {

            firebaseService.saveToHistory(image, khat)
            emit(Result.Success(null))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }

    override fun getHistory() = flow {
        emit(Result.Loading())
        try {
            val result = firebaseService.getHistories()
            emit(Result.Success(result))

        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }

    override fun getDetailHistory(id: String) = flow {
        emit(Result.Loading())
        try {
            val result = firebaseService.getDetailHistory(id)
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }
}