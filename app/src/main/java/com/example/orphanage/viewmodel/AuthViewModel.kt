package com.example.orphanage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orphanage.AuthRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    private val _loginResult = MutableStateFlow<Result<FirebaseUser>?>(null)
    val loginResult: StateFlow<Result<FirebaseUser>?> = _loginResult

    private val _registrationResult = MutableStateFlow<Result<FirebaseUser>?>(null)
    val registrationResult: StateFlow<Result<FirebaseUser>?> = _registrationResult

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = authRepository.signInWithEmailAndPassword(email, password)
        }
    }

    fun registerUser(email: String, password: String, username: String, role: String) {
        viewModelScope.launch {
            _registrationResult.value = authRepository.createUserWithEmailAndPassword(email, password, username, role)
        }
    }
}