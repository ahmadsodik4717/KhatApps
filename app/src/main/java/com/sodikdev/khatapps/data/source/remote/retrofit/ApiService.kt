package com.sodikdev.khatapps.data.source.remote.retrofit

import com.sodikdev.khatapps.data.source.remote.response.KhatResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("/detection")
    suspend fun scanKhat(
        @Part file: MultipartBody.Part?
    ): KhatResponse
}