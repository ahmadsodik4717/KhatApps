package com.sodikdev.khatapps.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.photo_profile),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
        )
        Text(text = "Nama Pembuat")
        Text(text = "Ahmad Sodik")
        Text(text = "Jurusan")
        Text(text = "Teknik Informatika")
        Text(text = "Universitas")
        Text(text = "Universitas Sains Al-Qur'an")
        Text(text = "Deskripsi")
        Text(text = "KhatApps adalah aplikasi Android yang memanfaatkan algoritma Convolutional Neural Network (CNN) untuk mengenali tiga jenis khat kaligrafi populer: Naskhi, Diwani, dan Tsuluts. Aplikasi ini dirancang untuk membantu pengguna, baik pemula maupun ahli, dalam mengidentifikasi jenis khat secara cepat dan akurat melalui fitur unggah gambar atau kamera langsung. Dengan antarmuka yang sederhana, KhatApps menjadi alat edukatif yang ideal untuk pembelajaran, penelitian, dan eksplorasi seni kaligrafi Islam.",
            textAlign = TextAlign.Justify)
    }

}