package eu.tortitas.deliverydam2.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.core.navigation.Navigator
import eu.tortitas.deliverydam2.login.domain.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email

    private val _emailModified = MutableStateFlow(false)
    val emailModified = _emailModified

    val emailError = _email.map { email ->
        if (email.isEmpty()) {
            "Email is required"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "Email is not valid"
        } else {
            null
        }
    }

    private val _password = MutableStateFlow("")
    val password = _password

    val _passwordModified = MutableStateFlow(false)
    val passwordModified = _passwordModified

    val passwordError = _password.map { password ->
        if (password.isEmpty()) {
            "Password is required"
        } else if (password.length < 8) {
            "Password must be at least 8 characters long"
        } else {
            null
        }
    }

    private val _loading = MutableStateFlow(false)
    val loading = _loading

    fun onEmailOrPasswordChanged(email: String, password: String) {
        if (email != _email.value) {
            _emailModified.value = true
        }

        if (password != _password.value) {
            _passwordModified.value = true
        }

        _email.value = email
        _password.value = password
    }

    fun onNavigateToRegister() {
        navigator.navigate("register")
    }

    fun onLogin() {
        _emailModified.value = true
        _passwordModified.value = true

        if (_loading.value) {
            return
        }

        _loading.value = true

        viewModelScope.launch {
            if (emailError.firstOrNull() != null || passwordError.firstOrNull() != null) {
                _loading.value = false
                return@launch
            }

            withContext(Dispatchers.IO) {
                Thread.sleep(2000)
            } // Simulate network delay

            val logged = loginUseCase(email.value, password.value)
            if (logged) {
                navigator.navigate("restaurant")
            }

            _loading.value = false
        }
    }
}
