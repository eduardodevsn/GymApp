package com.edudev.gymapp.data.repository

import com.edudev.gymapp.data.remote.ApiService
import com.edudev.gymapp.data.remote.dto.TrainingLevel
import com.edudev.gymapp.data.remote.dto.UpdateUserProfileRequest
import com.edudev.gymapp.data.remote.dto.UserProfileResponse
import com.edudev.gymapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ApiService
) : ProfileRepository {

    override fun getTrainingLevels(): Flow<Resource<List<TrainingLevel>>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(api.getTrainingLevels().trainingLevels))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error de conexión"))
        }
    }

    override fun updateProfile(request: UpdateUserProfileRequest): Flow<Resource<UserProfileResponse>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(api.updateMyProfile(request)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Error de conexión"))
        }
    }
}