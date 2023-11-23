package com.example.text_scanner

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.text_scanner.routing.Navigation
import com.example.text_scanner.ui.theme.Text_ScannerTheme


class MainActivity : ComponentActivity() {
    private lateinit var cameraLauncher: ActivityResultLauncher<String>
    private lateinit var galleryLauncher: ActivityResultLauncher<Intent>
    private val CAMERA_REQUEST_CODE = 123 // Use your preferred request code
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            Log.i("cam","camera")
            if (isGranted) {
                Log.i("cam","cameraaaa")
                // Permission granted, you can open the camera here
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                // Check if there is a camera app available to handle the intent
                if (cameraIntent.resolveActivity(packageManager) != null) {
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
                }
            }
        }
        galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == ComponentActivity.RESULT_OK) {
                val selectedImageUri = result.data?.data
                val imageUri = selectedImageUri?.toString()
                if (imageUri != null) {
                    TS_manager.sharedInstance.selectedImageUri.value = imageUri
                    TS_manager.sharedInstance.isImageSelected.value = true

                }
            }
        }
        setContent {
            Text_ScannerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(cameraLauncher, galleryLauncher)
                }
            }
        }
    }
}


