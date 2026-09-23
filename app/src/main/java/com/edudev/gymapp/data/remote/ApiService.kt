package com.edudev.gymapp.data.remote

import com.edudev.gymapp.data.remote.dto.AuthResponse
import com.edudev.gymapp.data.remote.dto.LoginRequest
import com.edudev.gymapp.data.remote.dto.RegisterRequest
import com.edudev.gymapp.data.remote.dto.TrainingLevelResponse
import com.edudev.gymapp.data.remote.dto.UpdateUserProfileRequest
import com.edudev.gymapp.data.remote.dto.UserProfileResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.PUT

interface ApiService {

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthResponse

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @GET("api/training-level")
    suspend fun getTrainingLevels(): TrainingLevelResponse

    @GET("api/users/me/profile")
    suspend fun getMyProfile(): UserProfileResponse

    @PUT("api/users/me/profile")
    suspend fun updateMyProfile(@Body request: UpdateUserProfileRequest): UserProfileResponse
}