package com.sodikdev.khatapps.ui.screen.scan_result

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ScanResultScreen(
    navController: NavController,
    imageUri: Uri,
    recognizedText: String
) {
    ScanResultContent(
        imageUri = imageUri,
        recognizedText = recognizedText
    )
}

@Composable
fun ScanResultContent(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    recognizedText: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = imageUri,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Recognized Text:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = recognizedText,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
