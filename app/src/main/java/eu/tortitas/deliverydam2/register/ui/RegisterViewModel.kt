package eu.tortitas.deliverydam2.register.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.core.navigation.Navigator
import eu.tortitas.deliverydam2.login.domain.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigator: Navigator,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email

    private val _password = MutableStateFlow("")
    val password = _password

    private val _passwordConfirmation = MutableStateFlow("")
    val passwordConfirmation = _passwordConfirmation

    private val _loading = MutableStateFlow(false)
    val loading = _loading

    fun onEmailOrPasswordChanged(email: String, password: String, passwordConfirmation: String) {
        _email.value = email
        _password.value = password
        _passwordConfirmation.value = passwordConfirmation
    }

    fun onNavigateToLogin() {
        navigator.navigate("login")
    }

    fun onRegister() {
        _loading.value = true

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Thread.sleep(1000)
            } // Simulate network delay

            if (registerUseCase(email.value, password.value, passwordConfirmation.value)) {
                navigator.navigate("login")
            }

            _loading.value = false
        }
    }
}
