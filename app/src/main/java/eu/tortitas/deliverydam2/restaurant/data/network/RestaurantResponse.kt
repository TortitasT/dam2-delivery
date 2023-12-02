package eu.tortitas.deliverydam2.restaurant.data.network

import com.google.gson.annotations.SerializedName

/*
  {
    "id": 5,
    "name": "Big Twins Diputación",
    "description": "En Smash Burger Big Twins, hemos perfeccionado el arte de la hamburgu
esa estilo «smash burger». Nuestro enfoque innovador consiste en aplastar cuidadosamente
la carne fresca en la parrilla caliente, lo que crea una costra dorada y crujiente en el
exterior, mientras que el interior se mantiene jugoso y lleno de sabor. Cada bocado es un
a explosión de sabores irresistibles que te dejará con ganas de más.\n\nPero en Smash Bur
ger Big Twins, no solo nos enfocamos en la comida excepcional, también nos esforzamos por
 brindar un servicio excepcional. Nuestro personal amable y atento está comprometido en h
acer que tu visita sea inolvidable. Desde el momento en que entras por nuestras puertas,
serás recibido con una sonrisa y un ambiente acogedor. Nuestro objetivo es asegurarnos de
 que te sientas como en casa y disfrutes de una experiencia gastronómica de primera categ
oría.",
    "address": "Carrer del Tucuman, 12, 03005 Alacant",
    "cover_url": "https://res.cloudinary.com/tf-lab/image/upload/w_600,h_310,c_fill,g_aut
o:subject,q_auto,f_auto/restaurant/42414cd4-0208-4e27-9cce-499978a299dd/3e427c22-196f-439
e-a0e0-65afa19303bb.jpg",
    "galery_urls": null,
    "created_at": "2023-11-26T18:32:14.504242+00:00",
    "update_at": "2023-11-26T18:32:14.504242+00:00"
  }
 */

data class RestaurantResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("cover_url")
    val coverUrl: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("update_at")
    val updateAt: String,

    var dishes: List<DishResponse>
)