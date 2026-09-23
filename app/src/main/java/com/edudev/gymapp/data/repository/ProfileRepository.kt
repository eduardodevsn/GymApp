package com.edudev.gymapp.data.repository

import com.edudev.gymapp.data.remote.dto.TrainingLevel
import com.edudev.gymapp.data.remote.dto.UpdateUserProfileRequest
import com.edudev.gymapp.data.remote.dto.UserProfileResponse
import com.edudev.gymapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getTrainingLevels(): Flow<Resource<List<TrainingLevel>>>
    fun updateProfile(request: UpdateUserProfileRequest): Flow<Resource<UserProfileResponse>>
}