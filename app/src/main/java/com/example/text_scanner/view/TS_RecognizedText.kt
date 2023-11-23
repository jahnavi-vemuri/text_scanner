package com.example.text_scanner.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.text_scanner.R
import com.example.text_scanner.routing.Screen
import com.example.text_scanner.helpers.saveTextToFile
import com.example.text_scanner.helpers.sendTextByEmail


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayRecognizedText(
    recognizedText: String,
    navController: NavController,
) {
    var isSaveButtonClicked by remember { mutableStateOf(false) }
    var filePath by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.DisplayStartingPage.route)
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = recognizedText,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            if (!isSaveButtonClicked) {
                Button(
                    onClick = {
                        val savedFilePath = saveTextToFile(context, recognizedText)
                        isSaveButtonClicked = true
                        filePath = savedFilePath.toString()
                    }
                ) {
                    Text(stringResource(id = R.string.save))
                }
            } else {
                Button(
                    onClick = {
                        sendTextByEmail(context, filePath)
                    }
                ) {
                    Text(stringResource(id = R.string.share))
                }
            }
        }
    }
}

