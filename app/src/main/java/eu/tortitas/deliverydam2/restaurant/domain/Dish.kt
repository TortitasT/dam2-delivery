package eu.tortitas.deliverydam2.restaurant.domain

data class Dish(
    val id: Int,

    val name: String,
    val description: String,
    val ingredients: List<String>,
    val allergens: List<String>,
    val coverUrl: String,

    val createdAt: String,
    val updateAt: String
)