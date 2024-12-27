package com.sodikdev.khatapps.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.provider.MediaStore
import com.sodikdev.khatapps.ml.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

suspend fun detectKhat(context: Context, uri: Uri): DetectResult? {
    val model = Model.newInstance(context)
    return try {
        val bitmap = uri.toBitmap(context)
        val byteBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3).apply {
            order(ByteOrder.nativeOrder())
        }

        for (y in 0 until 224) {
            for (x in 0 until 224) {
                val pixel = bitmap.getPixel(x, y)

                // Extract color channels and normalize
                byteBuffer.putFloat(Color.red(pixel) / 255.0f)
                byteBuffer.putFloat(Color.green(pixel) / 255.0f)
                byteBuffer.putFloat(Color.blue(pixel) / 255.0f)
            }
        }

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        // Step 3: Run inference
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        // Step 4: Get the confidence scores
        val confidences = outputFeature0.floatArray

        // Step 5: Find the index of the highest confidence score
        val maxIndex = confidences.indices.maxByOrNull { confidences[it] } ?: -1
        val confidence = if (maxIndex != -1) confidences[maxIndex] else 0.0f

        // Step 6: Map the index to a label (ensure labels match your model's classes)
        val labels = listOf("Diwani", "Naskh", "Thuluth")
        val diseaseName = if (maxIndex >= 0 && maxIndex < labels.size) labels[maxIndex] else "Unknown"

        // Step 7: Return the result with actual confidence score
        DetectResult(diseaseName = diseaseName, confidence = confidence)
    } catch (e: Exception) {
        null
    } finally {
        // Close the model to release resources
        model.close()
    }
}

@Parcelize
data class DetectResult(
    val diseaseName: String,
    val confidence: Float,
    val detectionTime: Long = 0,
) : Parcelable

@Suppress("DEPRECATION")
suspend fun Uri.toBitmap(context: Context): Bitmap = withContext(Dispatchers.IO) {
    val contentResolver = context.contentResolver
    return@withContext if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val source = ImageDecoder.createSource(contentResolver, this@toBitmap)
        ImageDecoder.decodeBitmap(source).copy(Bitmap.Config.ARGB_8888, true)
    } else {
        MediaStore.Images.Media.getBitmap(contentResolver, this@toBitmap)
    }

//    val byteArray = bitmapToByteArray(bitmap)
//    return@withContext if (byteArray.size > 1024 * 1024) { // If larger than 1 MB, compress it
//        compressBitmapToMaxSize(bitmap, 1000 * 1024) // Compress to max 100 KB
//    } else {
//        bitmap // If below 1 MB, return the original bitmap
//    }
}
private fun resizeBitmap(bitmap: Bitmap): Bitmap {
    return Bitmap.createScaledBitmap(bitmap, 224, 224, true)
}
private fun validateInput(bitmap: Bitmap): Boolean {
    return bitmap.width > 0 && bitmap.height > 0
}

private fun normalizePixel(pixel: Int): Float {
    return (pixel - 127.5f) / 127.5f
}

