package com.sodikdev.khatapps.util

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

class TextRecognitionUtil {
    private val recognizer: TextRecognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    suspend fun recognizeText(context: Context, uri: Uri): String {
        return withContext(Dispatchers.IO) {
            try {
                val image = InputImage.fromFilePath(context, uri)
                val result = suspendCancellableCoroutine { continuation ->
                    recognizer.process(image)
                        .addOnSuccessListener { text ->
                            continuation.resume(text.text)
                        }
                        .addOnFailureListener { e ->
                            continuation.resume("")
                        }
                        .addOnCanceledListener {
                            continuation.resume("")
                        }
                }
                result
            } catch (e: Exception) {
                ""
            }
        }
    }
}
