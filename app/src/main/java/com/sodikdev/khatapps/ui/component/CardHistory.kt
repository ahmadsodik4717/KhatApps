package com.sodikdev.khatapps.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import coil.compose.AsyncImage
import com.sodikdev.khatapps.data.model.History

@Composable
fun CardHistory(
    modifier: Modifier = Modifier,
    history: History
) {
    OutlinedCard(
        elevation = CardDefaults.elevatedCardElevation(3.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
        ) {
            AsyncImage(
                model = history.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(120.dp)
            )
            Spacer(modifier = modifier.size(8.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
            ) {
                Text(
                    text = "",
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = modifier
                        .padding(top = 8.dp)
                )

                Text(
                    text = "Confidence : $",
                )

                Spacer(modifier = modifier.size(16.dp))

                Text(
                    text = "",
                    textAlign = TextAlign.End,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, end = 8.dp)
                )
            }
        }
    }
}