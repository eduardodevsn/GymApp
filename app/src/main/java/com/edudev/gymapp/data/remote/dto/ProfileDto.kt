package com.edudev.gymapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TrainingLevel(
    val id: Int,
    val code: String,
    val name: String,
    val description: String? = null,
    val imageUrl: String? = null,
    val active: Boolean = true
)

@Serializable
data class TrainingLevelResponse(
    val trainingLevels: List<TrainingLevel> = emptyList()
)

@Serializable
data class UpdateUserProfileRequest(
    val phone: String? = null,
    val birthDate: String? = null, // yyyy-MM-dd
    val gender: String? = null,
    val heightCm: Double? = null,
    val currentWeightKg: Double? = null,
    val objective: String? = null,
    val medicalNotes: String? = null,
    val trainingLevelId: Int? = null
)

@Serializable
data class UserProfileResponse(
    val id: String,
    val userId: String,
    val fullName: String,
    val email: String,
    val phone: String? = null,
    val birthDate: String? = null,
    val gender: String? = null,
    val heightCm: Double? = null,
    val currentWeightKg: Double? = null,
    val trainingLevel: TrainingLevel? = null,
    val objective: String? = null,
    val medicalNotes: String? = null
)