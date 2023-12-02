package eu.tortitas.deliverydam2.restaurant.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.core.navigation.Navigator
import eu.tortitas.deliverydam2.domain.Restaurant
import eu.tortitas.deliverydam2.restaurant.domain.GetRestaurantsWithDishesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getRestaurantsWithDishesUseCase: GetRestaurantsWithDishesUseCase,
) : ViewModel() {
    private val _restaurant: MutableStateFlow<Restaurant?> = MutableStateFlow(null)
    val restaurant = _restaurant

    fun onLogout() {
        navigator.navigate("login")
    }

    fun loadRestaurant(id: Int) {
        viewModelScope.launch {
            _restaurant.value = getRestaurantsWithDishesUseCase(id)
        }
    }

    fun onClickedOnDish(dishId: Int) {
        navigator.navigate("dish/$dishId")
    }
}
