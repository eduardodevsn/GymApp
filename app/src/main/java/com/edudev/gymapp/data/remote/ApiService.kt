package com.edudev.gymapp.data.remote

import com.edudev.gymapp.data.remote.dto.AuthResponse
import com.edudev.gymapp.data.remote.dto.LoginRequest
import com.edudev.gymapp.data.remote.dto.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    // TODO Fase 3: agregar exercises, routines, chat, subscriptions, etc.
}