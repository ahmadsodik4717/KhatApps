package com.sodikdev.khatapps.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sodikdev.khatapps.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashScreen(onTimeOut: () -> Unit) {
    val currentOnTimeOut by rememberUpdatedState(onTimeOut)
    val isDarkTheme = androidx.compose.foundation.isSystemInDarkTheme()

    // Timer untuk splash screen
    LaunchedEffect(currentOnTimeOut) {
        delay(2.seconds)
        currentOnTimeOut()
    }

    // Menentukan logo berdasarkan tema
    val logo = if (isDarkTheme) {
        R.drawable.logowhite3 // Logo terang untuk mode gelap
    } else {
        R.drawable.logoblack3 // Logo gelap untuk mode terang
    }

    // Tampilan utama splash screen
    Box(
        modifier = Modifier
            .fillMaxSize(), // Mengisi seluruh layar
        contentAlignment = Alignment.Center // Semua isi Box berada di tengah
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Logo aplikasi
            Image(
                painter = painterResource(id = logo),
                contentDescription = "Logo Splash Screen",
                modifier = Modifier.size(200.dp) // Ukuran logo
            )

            /*// Teks di bawah logo
            Text(
                text = "KHAT SCAN",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )*/
        }
    }
}