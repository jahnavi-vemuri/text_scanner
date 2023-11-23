package com.example.text_scanner.routing

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.text_scanner.helpers.recognizedText
import com.example.text_scanner.view.DisplayRecognizedText
import com.example.text_scanner.view.StartingPage

@Composable
fun Navigation(
    cameraLauncher: ActivityResultLauncher<String>,
    galleryLauncher: ActivityResultLauncher<Intent>
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.DisplayStartingPage.route) {
        composable(route = Screen.DisplayStartingPage.route) {
            StartingPage(cameraLauncher, galleryLauncher, navController)
        }
        composable(route = Screen.DisplayText.route) {
            DisplayRecognizedText(recognizedText, navController)
        }
    }
}


