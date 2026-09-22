package com.edudev.gymapp.data.remote

import com.edudev.gymapp.data.remote.dto.LoginRequest
import com.edudev.gymapp.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

}