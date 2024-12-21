package com.sodikdev.khatapps.ui.screen.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sodikdev.khatapps.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.File
import javax.inject.Inject
import com.sodikdev.khatapps.util.Result
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private var classifyJob: Job? = null

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnScanUpload -> classify(event.photoFile, event.photoUri)
            is HomeEvent.ResetState -> _state.update {
                HomeState()
            }
            is HomeEvent.OnSaveImageUri -> _state.update {
                it.copy(
                    imageUri = event.imageUri
                )
            }
            is HomeEvent.OnCancelClassify -> {
                classifyJob?.cancel()
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun classify(photoFile: File, photoUri: Uri) {
        classifyJob = viewModelScope.launch {
            userRepository.classification(photoFile, photoUri).collect { result ->
                when (result) {
                    is Result.Error -> _state.update {
                        it.copy(
                            isLoading = false,
                            loadingMessage = null,
                            loadingProgress = null,
                            isError = true,
                            errorMessage = result.message
                        )
                    }
                    is Result.Loading -> _state.update {
                        it.copy(
                            isLoading = true,
                            isError = false,
                            errorMessage = null,
                            loadingMessage = result.message,
                            loadingProgress = result.title
                        )
                    }
                    is Result.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                loadingMessage = null,
                                isError = true,
                                errorMessage = null,
                                loadingProgress = null,
                                classifyResult = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        classifyJob = null
    }
}