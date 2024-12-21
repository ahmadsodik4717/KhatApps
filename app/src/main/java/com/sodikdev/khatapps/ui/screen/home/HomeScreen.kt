package com.sodikdev.khatapps.ui.screen.home

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CameraAlt
import androidx.compose.material.icons.rounded.Photo
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sodikdev.khatapps.BuildConfig
import com.sodikdev.khatapps.ui.navigation.Screen
import com.sodikdev.khatapps.util.createImageFile
import com.sodikdev.khatapps.util.toFile
import kotlinx.coroutines.launch
import java.io.File

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    state.errorMessage?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

//    if (state.classifyResult.isNotEmpty()) {
//        LaunchedEffect(key1 = Unit) {
//            navController.currentBackStackEntry?.savedStateHandle?.set(
//                "result",
//                state.classifyResult
//            )
//            navController.currentBackStackEntry?.savedStateHandle?.set("image_uri", state.imageUri)
//            Log.d("IMAGE", "ScanScreen: ${state.imageUri}")
//            navController.navigate(Screen.ScanResult)
////            viewModel.onEvent(HomeEvent.ResetState)
//        }
//    }

    HomeContent(
        imageUri = state.imageUri,
        onImageSelected = { file, uri ->
            viewModel.onEvent(HomeEvent.OnSaveImageUri(uri))
            val imageFile = file
//            if (imageFile != null) {
//                viewModel.onEvent(HomeEvent.OnScanUpload(imageFile, uri))
//            } else {
//                Toast.makeText(context, "Gagal memproses gambar!", Toast.LENGTH_SHORT).show()
//            }
        },
        uploadImage = {
            navController.currentBackStackEntry?.savedStateHandle?.set("image_uri", state.imageUri)
            Log.d("IMAGE", "ScanScreen: ${state.imageUri}")
            navController.navigate(Screen.ScanResult.route)
        },
        viewModel = viewModel
    )

}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    onImageSelected: (File, Uri) -> Unit,
    uploadImage: (Uri) -> Unit,
    viewModel: HomeViewModel
) {
    var currentPhotoUri: Uri? by remember { mutableStateOf(null) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val file = remember { context.createImageFile() }
    val uri = remember {
        FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.provider",
            file
        )
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {

        Log.d("GAMBAR", "HomeContent: $uri")

        val launcherPickPhoto = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickVisualMedia(),
        ) { result ->
            if (result != null) {
                scope.launch {
                    onImageSelected(
                        result.toFile(context),
                        result
                    )
                }
            }
        }

        val launcherTakePhoto = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture(),
        ) { result ->
            if (result) {
                onImageSelected(
                    uri.toFile(context),
                    uri
                )
            }
        }

        val launcherPermission = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { result ->
            if (result) {
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                launcherTakePhoto.launch(uri)
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

        AsyncImage(
            model = imageUri ?: "",
            contentDescription = "",
            modifier = Modifier
                .size(280.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            OutlinedButton(
                onClick = {
                    launcherPickPhoto.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = modifier
                    .weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Photo,
                    contentDescription = ""
                )

                Spacer(modifier = modifier.size(8.dp))

                Text(
                    text = "Galeri"
                )
            }

            ElevatedButton(
                onClick = {
                    val checkSelfPermission = ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.CAMERA
                    )
                    if (checkSelfPermission == PackageManager.PERMISSION_GRANTED) {
                        launcherTakePhoto.launch(uri)
                    } else {
                        launcherPermission.launch(Manifest.permission.CAMERA)
                    }
                },
                modifier = modifier
                    .weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Rounded.CameraAlt,
                    contentDescription = ""
                )

                Spacer(modifier = modifier.size(8.dp))

                Text(
                    text = "Kamera"
                )
            }
        }

        OutlinedButton(
            onClick = {
                if (imageUri != null) {
                    viewModel.onEvent(HomeEvent.OnScanUpload(file, imageUri, context))
                    uploadImage(imageUri)
                }
            },
            enabled = imageUri != null,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.Upload,
                contentDescription = ""
            )

            Spacer(modifier = modifier.size(8.dp))

            Text(
                text = "Upload"
            )
        }
    }
}