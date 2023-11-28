package eu.tortitas.deliverydam2.register.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.core.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email

    private val _password = MutableStateFlow("")
    val password = _password

    private val _passwordConfirmation = MutableStateFlow("")
    val passwordConfirmation = _passwordConfirmation

    fun onEmailOrPasswordChanged(email: String, password: String, passwordConfirmation: String) {
        _email.value = email
        _password.value = password
        _passwordConfirmation.value = passwordConfirmation
    }

    fun onNavigateToLogin() {
        navigator.navigate("login")
    }

    fun onRegister() {
        navigator.navigate("products")
    }
}
