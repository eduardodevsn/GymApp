package com.edudev.gymapp.data.repository

import com.edudev.gymapp.data.remote.dto.AuthResponse
import com.edudev.gymapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String): Flow<Resource<AuthResponse>>
    fun register(email: String, fullName: String, password: String): Flow<Resource<AuthResponse>>
}