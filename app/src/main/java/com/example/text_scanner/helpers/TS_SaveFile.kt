package com.example.text_scanner.helpers

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileOutputStream

fun saveTextToFile(context: Context, text: String): File? {
    try {
        // Get the app-specific directory for your app
        val directory = File(context.getExternalFilesDir(null), "my_directory_name")

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs()
        }

        // Create the file to save the text
        val file = File(directory, "recognized_text.txt")
        Log.i("mydir", "$file")

        // Write the text to the file
        val fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(text.toByteArray())
        fileOutputStream.close()

        return file // Return the File object
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return null
}