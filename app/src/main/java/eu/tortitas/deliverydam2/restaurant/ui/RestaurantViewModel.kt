package eu.tortitas.deliverydam2.restaurant.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.core.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel() {
    fun onLogout() {
        navigator.navigate("login")
    }
}
