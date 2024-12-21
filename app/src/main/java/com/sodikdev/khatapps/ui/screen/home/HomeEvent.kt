package com.sodikdev.khatapps.ui.screen.home

import android.net.Uri
import java.io.File

sealed class HomeEvent{
    data class OnScanUpload(
        val photoFile: File,
        val photoUri: Uri,
    ) : HomeEvent()
    data object ResetState : HomeEvent()
    data class OnSaveImageUri(
        val imageUri: Uri?
    ) : HomeEvent()
    data object OnCancelClassify : HomeEvent()
}