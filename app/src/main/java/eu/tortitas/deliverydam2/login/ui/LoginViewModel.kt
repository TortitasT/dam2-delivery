package eu.tortitas.deliverydam2.login.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.core.navigation.Navigator
import eu.tortitas.deliverydam2.login.domain.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val _password = MutableStateFlow("")
    val password = _password

    private val _loading = MutableStateFlow(false)
    val loading = _loading

    fun onEmailOrPasswordChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
    }

    fun onNavigateToRegister() {
        navigator.navigate("register")
    }

    fun onLogin() {
        _loading.value = true

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Thread.sleep(2000)
            } // Simulate network delay

            val logged = loginUseCase(email.value, password.value)
            if (logged) {
                Log.i("LoginViewModel", "Logged in")
                //navigator.navigate("products")
            }

            _loading.value = false
        }
    }
}
