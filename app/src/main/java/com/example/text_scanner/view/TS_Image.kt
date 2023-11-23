package com.example.text_scanner.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.text_scanner.R
import com.example.text_scanner.TS_manager
import com.example.text_scanner.routing.Screen
import com.example.text_scanner.helpers.recognizeTextFromImage

@SuppressLint("UnrememberedMutableState", "SuspiciousIndentation")
@Composable
fun ImageScreen(imageUri: String?, navController: NavController) {
    val context = LocalContext.current
    var selectedImageUri = Uri.parse(imageUri)
    val bitmap: Bitmap =
        MediaStore.Images.Media.getBitmap(context.contentResolver, selectedImageUri)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(50.dp))
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(400.dp).width(400.dp)
        )
        Spacer(modifier = Modifier.padding(30.dp))
        Button(onClick = {
            selectedImageUri = null
            TS_manager.sharedInstance.selectedImageUri.value = null
            TS_manager.sharedInstance.isImageSelected.value = false
            navController.navigate(Screen.DisplayStartingPage.route)
        }) {
            Text(stringResource(id = R.string.capture))
        }
        Button(onClick = {
            recognizeTextFromImage(bitmap)
            navController.navigate(Screen.DisplayText.route)
        }) {
            Text(stringResource(id = R.string.scan))
        }
    }
}
