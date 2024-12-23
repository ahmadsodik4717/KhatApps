package com.sodikdev.khatapps.util

import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        "JPEG_${timeStamp}_",
        ".jpg",
        storageDir
    ).apply {
        createNewFile()
    }
}


@Suppress("DEPRECATION")
fun Uri.toFile(context: Context): File {
    val contentResolver = context.contentResolver
    val myFile = createTempFile("image", "jpg")

    val inputStream = contentResolver.openInputStream(this) as InputStream
    val outputStream = FileOutputStream(myFile)
    val buf = ByteArray(1024)
    var len: Int

    while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
    outputStream.close()
    inputStream.close()

    return myFile
}