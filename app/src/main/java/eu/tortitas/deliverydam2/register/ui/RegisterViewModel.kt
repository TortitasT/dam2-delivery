package eu.tortitas.deliverydam2.register.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.core.navigation.Navigator
import eu.tortitas.deliverydam2.login.domain.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigator: Navigator,
    private val registerUseCase: RegisterUseCase,
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
        } else if (password.length < 6) {
            "Password must be at least 6 characters long"
        } else {
            null
        }
    }

    private val _passwordConfirmation = MutableStateFlow("")
    val passwordConfirmation = _passwordConfirmation

    val _passwordConfirmationModified = MutableStateFlow(false)
    val passwordConfirmationModified = _passwordConfirmationModified

    val passwordConfirmationError = _passwordConfirmation.map { passwordConfirmation ->
        if (passwordConfirmation.isEmpty()) {
            "Password confirmation is required"
        } else if (passwordConfirmation != password.value) {
            "Password confirmation must match password"
        } else {
            null
        }
    }

    private val _loading = MutableStateFlow(false)
    val loading = _loading

    private val _popupShown = MutableStateFlow(false)
    val popupShown = _popupShown

    private val _popupText = MutableStateFlow("")
    val popupText = _popupText

    fun onEmailOrPasswordChanged(email: String, password: String, passwordConfirmation: String) {
        if (_email.value != email) {
            _emailModified.value = true
        }
        if (_password.value != password) {
            _passwordModified.value = true
        }
        if (_passwordConfirmation.value != passwordConfirmation) {
            _passwordConfirmationModified.value = true
        }

        _email.value = email
        _password.value = password
        _passwordConfirmation.value = passwordConfirmation
    }

    fun onNavigateToLogin() {
        navigator.navigate("login")
    }

    fun onPopupDismissed() {
        _popupShown.value = false
    }

    fun onRegister() {
        _emailModified.value = true
        _passwordModified.value = true
        _passwordConfirmationModified.value = true

        if (_loading.value) {
            return
        }

        _loading.value = true

        viewModelScope.launch {
            if (emailError.firstOrNull() != null ||
                passwordError.firstOrNull() != null ||
                passwordConfirmationError.firstOrNull() != null
            ) {
                _loading.value = false
                return@launch
            }

            if (registerUseCase(email.value, password.value, passwordConfirmation.value)) {
                navigator.navigate("login")
            } else {
                _popupText.value = "Error registering"
                _popupShown.value = true
            }

            _loading.value = false
        }
    }
}
