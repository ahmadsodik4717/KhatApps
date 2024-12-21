package com.sodikdev.khatapps.ui.screen.home

import android.net.Uri
import android.content.Context
import java.io.File

sealed class HomeEvent {
    data class OnScanUpload(
        val photoFile: File,
        val photoUri: Uri,
        val context: Context
    ) : HomeEvent()

    data object ResetState : HomeEvent()

    data class OnSaveImageUri(
        val imageUri: Uri
    ) : HomeEvent()

    data object OnCancelClassify : HomeEvent()
}
