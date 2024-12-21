package com.sodikdev.khatapps.ui.screen.scan_result

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ScanResultScreen(
    navController: NavController,
    imageUri: Uri
) {

    ScanResultContent(imageUri = imageUri)

}

@Composable
fun ScanResultContent(
    modifier: Modifier = Modifier,
    imageUri : Uri?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(text = "JANCUK")
        AsyncImage(
            model = imageUri,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
}