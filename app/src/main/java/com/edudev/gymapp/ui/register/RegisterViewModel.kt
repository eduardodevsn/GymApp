package com.edudev.gymapp.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edudev.gymapp.data.repository.AuthRepository
import com.edudev.gymapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val registered: Boolean = false
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun register(fullName: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.register(email, fullName, password).collect { result ->
                _uiState.value = when (result) {
                    is Resource.Loading -> _uiState.value.copy(isLoading = true, error = null)
                    is Resource.Success -> _uiState.value.copy(isLoading = false, registered = true)
                    is Resource.Error -> _uiState.value.copy(isLoading = false, error = result.message)
                }
            }
        }
    }
}