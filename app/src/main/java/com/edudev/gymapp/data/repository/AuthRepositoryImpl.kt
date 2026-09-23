package com.edudev.gymapp.data.repository

import com.edudev.gymapp.data.local.TokenManager
import com.edudev.gymapp.data.remote.ApiService
import com.edudev.gymapp.data.remote.dto.AuthResponse
import com.edudev.gymapp.data.remote.dto.LoginRequest
import com.edudev.gymapp.data.remote.dto.RegisterRequest
import com.edudev.gymapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val tokenManager: TokenManager
) : AuthRepository {

    override fun login(email: String, password: String): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.login(LoginRequest(email, password))
            tokenManager.saveTokens(response.accessToken, response.refreshToken)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error de conexión"))
        }
    }

    override fun register(email: String, fullName: String, password: String): Flow<Resource<AuthResponse>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.register(RegisterRequest(email, fullName, password))
            tokenManager.saveTokens(response.accessToken, response.refreshToken)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error de conexión"))
        }
    }
}