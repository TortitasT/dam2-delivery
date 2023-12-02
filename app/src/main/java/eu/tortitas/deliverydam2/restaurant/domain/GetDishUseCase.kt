package eu.tortitas.deliverydam2.restaurant.domain

import eu.tortitas.deliverydam2.data.RestaurantRepository
import eu.tortitas.deliverydam2.domain.Dish
import javax.inject.Inject

class GetDishUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(id: Int): Dish {
        val dish = restaurantRepository.getDish(id)

        return Dish(
            id = dish.id,
            name = dish.name,
            description = dish.description,
            ingredients = dish.ingredients,
            allergens = dish.allergens,
            coverUrl = dish.coverUrl,
            createdAt = dish.createdAt,
            updateAt = dish.updateAt
        )
    }

}
