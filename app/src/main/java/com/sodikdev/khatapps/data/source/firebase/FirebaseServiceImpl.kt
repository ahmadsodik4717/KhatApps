package com.sodikdev.khatapps.data.source.firebase

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.storage.FirebaseStorage
import com.sodikdev.khatapps.data.model.History
import com.sodikdev.khatapps.data.model.Khat
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class FirebaseServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : FirebaseService {
    override suspend fun getDiseaseById(khatId: String): Khat? {
        return firestore.collection("diseases")
            .document(khatId)
            .get()
            .await()
            .toObject()
    }

    override suspend fun saveToHistory(image: Uri, khats: List<Khat?>) {
        val storageRef = storage.reference.child("history/${Date().time}.jpg")
        val upload = storageRef.putFile(image).await()
        val downloadUrl = upload.metadata?.reference?.downloadUrl?.await()

        if (downloadUrl != null) {
            firestore.collection("histories")
                .document()
                .set(
                    History(
                        id = firestore.collection("histories").document().id,
                        imageUrl = downloadUrl.toString(),
                        khats = khats,
                    )
                ).await()
        } else {
            throw Exception("Gagal Unduh URL")
        }
    }

    override suspend fun getHistories(): List<History> {
        return firestore.collection("histories")
            .get()
            .await()
            .toObjects()
    }

    override suspend fun getDetailHistory(id: String): History? {
        return firestore.collection("histories")
            .whereEqualTo("id", id)
            .limit(1)
            .get()
            .await()
            .documents
            .firstOrNull()
            ?.toObject()
    }
}