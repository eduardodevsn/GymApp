package com.edudev.gymapp.ui.login

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

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val loggedIn: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect { result ->
                _uiState.value = when (result) {
                    is Resource.Loading -> _uiState.value.copy(isLoading = true, error = null)
                    is Resource.Success -> _uiState.value.copy(isLoading = false, loggedIn = true)
                    is Resource.Error -> _uiState.value.copy(isLoading = false, error = result.message)
                }
            }
        }
    }
}