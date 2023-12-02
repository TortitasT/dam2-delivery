package eu.tortitas.deliverydam2.restaurant.domain

import eu.tortitas.deliverydam2.data.RestaurantRepository
import eu.tortitas.deliverydam2.domain.Dish
import eu.tortitas.deliverydam2.domain.Restaurant
import javax.inject.Inject

class GetRestaurantsWithDishesUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(id: Int): Restaurant {
        val restaurantWithDishes = restaurantRepository.getRestaurantWithDishes(id)

        return Restaurant(
            id = restaurantWithDishes.id,
            name = restaurantWithDishes.name,
            description = restaurantWithDishes.description,
            address = restaurantWithDishes.address,
            coverUrl = restaurantWithDishes.coverUrl,
            dishes = restaurantWithDishes.dishes.map { dish ->
                Dish(
                    id = dish.id,
                    name = dish.name,
                    description = dish.description,
                    ingredients = dish.ingredients,
                    allergens = dish.allergens,
                    coverUrl = dish.coverUrl,
                    createdAt = dish.createdAt,
                    updateAt = dish.updateAt
                )
            },
            createdAt = restaurantWithDishes.createdAt,
            updateAt = restaurantWithDishes.updateAt
        )
    }
}
