package eu.tortitas.deliverydam2.register.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.Navigator
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    fun onNavigateToLogin() {
        navigator.navigate("login")
    }
}
