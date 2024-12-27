package com.sodikdev.khatapps.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Khat(
    val id: String = "",
    val images: List<String> = emptyList(),
    val name: String = "",
    val description: String = "",
    val author : String = "",
    val confidence: Double = 0.0
): Parcelable