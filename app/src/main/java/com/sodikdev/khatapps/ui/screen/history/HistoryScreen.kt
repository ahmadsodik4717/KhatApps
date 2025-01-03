package com.sodikdev.khatapps.ui.screen.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sodikdev.khatapps.data.model.History
import com.sodikdev.khatapps.ui.component.CardHistory

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

}


@Composable
fun HistoryContent(
    modifier: Modifier = Modifier,
    history: List<History>
) {

    LazyColumn {
        items(history, key = {}) {
            CardHistory(history = it)
        }
    }

}