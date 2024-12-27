package com.sodikdev.khatapps.ui.screen.home

import android.net.Uri
import com.sodikdev.khatapps.data.model.Khat
import com.sodikdev.khatapps.util.DetectResult

data class HomeState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val loadingMessage: String? = null,
    val loadingProgress: String? = null,
    val isError: Boolean = false,
    val classifyResult: List<Khat?> = emptyList(),
    val imageUri: Uri? = null,
    val detectionResult: DetectResult? = null,
    val errorMessage: String? = null,
)