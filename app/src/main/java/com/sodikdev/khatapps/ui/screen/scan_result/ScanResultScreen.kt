package com.sodikdev.khatapps.ui.screen.scan_result

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sodikdev.khatapps.data.model.Khat
import com.sodikdev.khatapps.util.DetectResult
import com.sodikdev.khatapps.util.TextRecognitionUtil

@Composable
fun ScanResultScreen(
    navController: NavController,
    imageUri: Uri,
    result: DetectResult
) {
    ScanResultContent(
        imageUri = imageUri,
        khat = result
    )
}

@Composable
fun ScanResultContent(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    khat: DetectResult
) {
    val context = LocalContext.current

    LaunchedEffect(imageUri) {
        Toast.makeText(context, "|$imageUri", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
            text = "Jenis Khat:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = khat.diseaseName,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = "Pencipta Khat:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = khat.author ?: "Unknown",
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        /*Text(
            text = "Confidence:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = khat.confidence.toString(),
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 4.dp)
        )*/

        Text(
            text = "Confidence:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = "${khat.confidencePercentage}%", // Menampilkan confidence sebagai persentase
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = "Deskripsi:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp)
        )

        Text(
            text = khat.description ?: "No description available",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Justify
        )
    }
}
