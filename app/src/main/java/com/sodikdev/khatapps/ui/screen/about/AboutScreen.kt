package com.sodikdev.khatapps.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sodikdev.khatapps.R

@Composable
fun AboutScreen(
    navController: NavController
) {
    AboutContent()
}

@Composable
fun AboutContent(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme()
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        val logo = if (isDarkTheme) {
            R.drawable.logowhite3 // Logo terang untuk mode gelap
        } else {
            R.drawable.logoblack3 // Logo gelap untuk mode terang
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, // Vertikal di tengah
            horizontalArrangement = Arrangement.spacedBy(16.dp), // Spasi antara gambar dan teks
            modifier = Modifier.fillMaxWidth()
        ) {
            // Gambar
            Image(
                painter = painterResource(id = logo),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(180.dp) // Ukuran gambar yang lebih kecil agar seimbang dengan teks
                    .clip(CircleShape)
            )
            // Teks
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Nama Pembuat",
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Ahmad Sodik")

                Text(
                    text = "Jurusan",
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Teknik Informatika")

                Text(
                    text = "Universitas",
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Universitas Sains Al-Qur'an")
            }
        }
        Text(text = "Deskripsi",
            fontWeight = FontWeight.Bold
        )
        Text(text = "KhatScan adalah aplikasi Android yang memanfaatkan" +
                " algoritma Convolutional Neural Network (CNN) " +
                "untuk mengenali tiga jenis khat kaligrafi: Naskhi, Diwani, dan Tsuluts. " +
                "Aplikasi ini dirancang untuk membantu pengguna, baik pemula maupun ahli, " +
                "dalam mengidentifikasi jenis khat secara cepat dan akurat " +
                "melalui fitur unggah gambar atau kamera langsung Dengan antarmuka yang sederhana.",
            textAlign = TextAlign.Justify)
    }

}