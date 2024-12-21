package com.sodikdev.khatapps.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    val id: String? = "",
    val imageUrl: String = "",
    val khats: List<Khat?> = emptyList(),
) : Parcelable