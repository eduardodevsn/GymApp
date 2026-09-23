package com.edudev.gymapp.ui.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edudev.gymapp.data.remote.dto.TrainingLevel
import com.edudev.gymapp.data.remote.dto.UpdateUserProfileRequest
import com.edudev.gymapp.data.repository.ProfileRepository
import com.edudev.gymapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

enum class WeightUnit { KG, LB }

data class SetupState(
    val gender: String? = null,
    val age: Int = 28,
    val weightValue: Int = 75,
    val weightUnit: WeightUnit = WeightUnit.KG,
    val heightCm: Int = 165,
    val objective: String? = null,
    val activityLevelLabel: String? = null,
    val fullName: String = "",
    val phone: String = "",
    val isSubmitting: Boolean = false,
    val submitError: String? = null,
    val submitted: Boolean = false
)

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SetupState())
    val state: StateFlow<SetupState> = _state.asStateFlow()

    private var trainingLevels: List<TrainingLevel> = emptyList()

    fun setGender(g: String) { _state.value = _state.value.copy(gender = g) }
    fun setAge(a: Int) { _state.value = _state.value.copy(age = a) }
    fun setWeight(v: Int, unit: WeightUnit) { _state.value = _state.value.copy(weightValue = v, weightUnit = unit) }
    fun setHeight(h: Int) { _state.value = _state.value.copy(heightCm = h) }
    fun setObjective(o: String) { _state.value = _state.value.copy(objective = o) }
    fun setActivityLevel(label: String) { _state.value = _state.value.copy(activityLevelLabel = label) }
    fun setFullName(name: String) { _state.value = _state.value.copy(fullName = name) }
    fun setPhone(phone: String) { _state.value = _state.value.copy(phone = phone) }

    fun submit() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isSubmitting = true, submitError = null)

            if (trainingLevels.isEmpty()) {
                profileRepository.getTrainingLevels().collect { result ->
                    if (result is Resource.Success) trainingLevels = result.data
                }
            }

            val s = _state.value
            val weightKg = if (s.weightUnit == WeightUnit.LB) s.weightValue * 0.453592 else s.weightValue.toDouble()
            val birthDate = LocalDate.now().minusYears(s.age.toLong())
                .format(DateTimeFormatter.ISO_LOCAL_DATE)
            val trainingLevelId = trainingLevels.firstOrNull {
                it.code.equals(mapActivityLabelToCode(s.activityLevelLabel), ignoreCase = true)
            }?.id

            val request = UpdateUserProfileRequest(
                phone = s.phone.ifBlank { null },
                birthDate = birthDate,
                gender = s.gender,
                heightCm = s.heightCm.toDouble(),
                currentWeightKg = weightKg,
                objective = s.objective,
                trainingLevelId = trainingLevelId
            )

            profileRepository.updateProfile(request).collect { result ->
                _state.value = when (result) {
                    is Resource.Loading -> _state.value.copy(isSubmitting = true)
                    is Resource.Success -> _state.value.copy(isSubmitting = false, submitted = true)
                    is Resource.Error -> _state.value.copy(isSubmitting = false, submitError = result.message)
                }
            }
        }
    }

    private fun mapActivityLabelToCode(label: String?) = when (label) {
        "Beginner" -> "BEGINNER"
        "Intermediate" -> "INTERMEDIATE"
        "Advance" -> "ADVANCED"
        else -> ""
    }
}