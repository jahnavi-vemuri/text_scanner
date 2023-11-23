package com.example.text_scanner.helpers

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

var recognizedText by mutableStateOf("")

fun recognizeTextFromImage(bitmap: Bitmap) {
    val options = TextRecognizerOptions.DEFAULT_OPTIONS
    val recognizer = TextRecognition.getClient(options)
    val image = InputImage.fromBitmap(bitmap, 0)
    recognizer.process(image)
        .addOnSuccessListener { text ->
            handleRecognizedText(text)
        }
        .addOnFailureListener { e ->
            Log.e("TextRecognition", "Text recognition error: $e")
        }
}

private fun handleRecognizedText(text: Text) {
    val recognizedTextBuilder = StringBuilder()
    for (block in text.textBlocks) {
        for (line in block.lines) {
            for (element in line.elements) {
                recognizedTextBuilder.append(element.text)
                recognizedTextBuilder.append(" ") // Add space between elements
            }
        }
    }
    recognizedText = recognizedTextBuilder.toString()
}



