package eu.tortitas.deliverydam2.login.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.Navigator
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    fun onNavigateToRegister() {
        navigator.navigate("register")
    }
}
