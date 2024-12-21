package com.sodikdev.khatapps.ui.screen.home

import android.net.Uri
import com.sodikdev.khatapps.data.model.Khat

data class HomeState(
    val isLoading: Boolean = false,
    val loadingMessage: String? = null,
    val loadingProgress: String? = null,
    val isError: Boolean = false,
    val classifyResult: List<Khat?> = emptyList(),
    val imageUri: Uri? = null,
    val recognizedText: String = "",
    val errorMessage: String? = null
)