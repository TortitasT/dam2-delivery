package eu.tortitas.deliverydam2.restaurant.data

import eu.tortitas.deliverydam2.restaurant.data.network.DishResponse
import eu.tortitas.deliverydam2.restaurant.data.network.RestaurantResponse
import eu.tortitas.deliverydam2.restaurant.data.network.RestaurantService
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val restaurantService: RestaurantService
) {
    suspend fun getRestaurantWithDishes(id: Int): RestaurantResponse =
        restaurantService.getRestaurantWithDishes(id)

    suspend fun getDish(id: Int): DishResponse =
        restaurantService.getDish(id)
}
