package eu.tortitas.deliverydam2.login.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.core.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email

    private val _password = MutableStateFlow("")
    val password = _password

    fun onEmailOrPasswordChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
    }

    fun onNavigateToRegister() {
        navigator.navigate("register")
    }

    fun onLogin() {
        navigator.navigate("products")
    }
}
