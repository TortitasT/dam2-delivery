package eu.tortitas.deliverydam2.restaurant.domain

data class Restaurant(
    val id: Int,

    val name: String,
    val description: String,
    val address: String,
    val coverUrl: String,
    val dishes: List<Dish>,

    val createdAt: String,
    val updateAt: String
)