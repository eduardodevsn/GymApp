package com.edudev.gymapp.data.repository

import com.edudev.gymapp.data.remote.ApiService
import com.edudev.gymapp.data.remote.dto.LoginRequest
import com.edudev.gymapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AuthRepository {

    override fun login(email: String, password: String): Flow<Resource<String>> = flow {
        emit(Resource.Loading)
        try {
            val response = api.login(LoginRequest(email, password))
            emit(Resource.Success(response.token))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error de conexión"))
        }
    }
}