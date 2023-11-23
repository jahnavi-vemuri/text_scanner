package com.example.text_scanner.helpers

import android.content.Context
import android.content.Intent
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

fun sendTextByEmail(context: Context, filePath: String) {
    val file = File(filePath)
    if (file.exists()) {
        try {
            val text = readTextFromFile(file)

            // Create an explicit Intent with ACTION_SEND
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain" // Set the type of file to be sent

            // Set the text as the email body
            intent.putExtra(Intent.EXTRA_TEXT, text)

            // Optionally, you can add a subject
            intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the Email")

            // Create a chooser to select an email app
            val chooserIntent = Intent.createChooser(intent, "Send Email")

            // Start the activity
            context.startActivity(chooserIntent)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

@Throws(IOException::class)
fun readTextFromFile(file: File): String {
    val text = StringBuilder()
    val br = BufferedReader(FileReader(file))
    var line: String?
    while (br.readLine().also { line = it } != null) {
        text.append(line)
        text.append('\n') // Add newline character if needed
    }
    br.close()
    return text.toString()
}


