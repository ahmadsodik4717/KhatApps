package com.sodikdev.khatapps.util

sealed class Result<out R> {
    data class Loading<out T>(
        val data: T? = null,
        val message: String? = null,
        val title: String? = null
    ) : Result<T>()

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String? = null) : Result<Nothing>()
}