package com.example.text_scanner

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TS_manager : ViewModel() {
    companion object {
        val sharedInstance = TS_manager()
    }
    val selectedImageUri = mutableStateOf<String?>(null)
    val isImageSelected = mutableStateOf(false)
}