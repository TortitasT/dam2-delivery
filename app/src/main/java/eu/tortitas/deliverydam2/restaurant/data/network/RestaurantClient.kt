package eu.tortitas.deliverydam2.restaurant.data.network

import retrofit2.Response
import retrofit2.http.GET

interface RestaurantClient {
    @GET("/rest/v1/dish?select=*")
    suspend fun restaurantDishRelations(): Response<List<RestaurantDishRelationResponse>>

    @GET("/rest/v1/restaurant?select=*")
    suspend fun restaurants(): Response<List<RestaurantResponse>>

    @GET("/rest/v1/food?select=*")
    suspend fun dishes(): Response<List<DishResponse>>
}
