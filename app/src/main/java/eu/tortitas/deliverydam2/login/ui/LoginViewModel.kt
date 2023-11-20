package eu.tortitas.deliverydam2.login.ui

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navHostController: NavHostController
) : ViewModel() {
    fun onNavigateToRegister() {
        navHostController.navigate("register")
    }
}
