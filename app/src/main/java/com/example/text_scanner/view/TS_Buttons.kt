package com.example.text_scanner.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.activity.result.ActivityResultLauncher
import android.content.Intent
import android.provider.MediaStore
import androidx.compose.ui.res.stringResource
import com.example.text_scanner.R
import com.example.text_scanner.TS_manager


@Composable
fun StartingPage(
    cameraLauncher: ActivityResultLauncher<String>,
    galleryLauncher: ActivityResultLauncher<Intent>,
    navController: NavController
) {
    val imageUri = TS_manager.sharedInstance.selectedImageUri.value
    if (imageUri != null) {
        ImageScreen(imageUri =imageUri, navController)
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        cameraLauncher.launch(android.Manifest.permission.CAMERA)
                    }
                ) {
                    Text(stringResource(id = R.string.camera))
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        galleryLauncher.launch(
                            Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            )
                        )
                    }
                ) {
                    Text(stringResource(id = R.string.gallery))
                }
            }
        }
    }
}
