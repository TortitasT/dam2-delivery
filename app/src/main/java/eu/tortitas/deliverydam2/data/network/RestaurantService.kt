package eu.tortitas.deliverydam2.data.network

import javax.inject.Inject

class RestaurantService @Inject constructor(
    private val restaurantClient: RestaurantClient
) {
    suspend fun getRestaurantsWithDishes(id: Int): List<RestaurantResponse> {
        val restaurantsResponse = restaurantClient.restaurants()
        val dishesResponse = restaurantClient.dishes()
        val restaurantDishRelationResponse = restaurantClient.restaurantDishRelations()

        if (!restaurantsResponse.isSuccessful || !restaurantDishRelationResponse.isSuccessful || !dishesResponse.isSuccessful) {
            throw Exception("Error getting restaurants with dishes: ${restaurantsResponse.code()} ${restaurantDishRelationResponse.code()} ${dishesResponse.code()}")
        }

        val restaurants = restaurantsResponse.body()!!
        val restaurantDishRelations = restaurantDishRelationResponse.body()!!
        val dishes = dishesResponse.body()!!

        val restaurantDishRelationsMap = restaurantDishRelations.groupBy { it.idRestaurant }

        return restaurants.map { restaurant ->
            restaurant.dishes = restaurantDishRelationsMap[restaurant.id]!!
                .map { restaurantDishRelation ->
                    dishes.find { it.id == restaurantDishRelation.idFood }!!
                }

            restaurant
        }
    }

    suspend fun getRestaurantWithDishes(id: Int): RestaurantResponse {
        val restaurantsWithDishes = getRestaurantsWithDishes(id)
        return restaurantsWithDishes.find { it.id == id }!!
    }

    suspend fun getDish(id: Int): DishResponse {
        val dishesResponse = restaurantClient.dishes()

        if (!dishesResponse.isSuccessful) {
            throw Exception("Error getting dish: ${dishesResponse.code()}")
        }

        val dishes = dishesResponse.body()!!

        return dishes.find { it.id == id }!!
    }
}