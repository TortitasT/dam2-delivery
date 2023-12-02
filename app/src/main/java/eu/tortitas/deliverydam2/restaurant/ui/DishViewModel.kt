package eu.tortitas.deliverydam2.restaurant.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.tortitas.deliverydam2.core.navigation.Navigator
import eu.tortitas.deliverydam2.restaurant.domain.Dish
import eu.tortitas.deliverydam2.restaurant.domain.GetDishUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DishViewModel @Inject constructor(
    private val getDishUseCase: GetDishUseCase,
    private val navigator: Navigator,
) : ViewModel() {
    val _dish = MutableStateFlow<Dish?>(null)
    val dish = _dish

    fun loadDish(dishId: Int) {
        viewModelScope.launch {
            _dish.value = getDishUseCase(dishId)
        }
    }

    fun onBack() {
        Log.i("DishViewModel", "onBack")
        navigator.back()
    }
}
