package io.github.pwlski04.allergytracer.ui

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import java.io.File

fun saveImageToApp(context: Context, source: Uri): Uri {
    val file = File(context.filesDir, "product_${System.currentTimeMillis()}.jpg")
    context.contentResolver.openInputStream(source)!!.use { input ->
        file.outputStream().use { output -> input.copyTo(output) }
    }
    return file.toUri()
}