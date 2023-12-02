package eu.tortitas.deliverydam2.restaurant.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eu.tortitas.deliverydam2.composables.Loader
import eu.tortitas.deliverydam2.composables.layouts.Default
import eu.tortitas.deliverydam2.restaurant.ui.composables.DishCard


@Composable
fun RestaurantScreen(
    restaurantViewModel: RestaurantViewModel,
    modifier: Modifier = Modifier,
) {
    val restaurant = restaurantViewModel.restaurant.collectAsState().value

    if (restaurant == null) {
        Loader()
        return
    }

    Default(
        onBack = { restaurantViewModel.onLogout() },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Outlined.FavoriteBorder, "Add to favorites")
            }
        },
        content = {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            )
            {
                Text(
                    text = restaurant.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier
                        .widthIn(0.dp, 300.dp)
                )

                AsyncImage(
                    model = restaurant.coverUrl,
                    contentDescription = "Restaurant image",
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .clip(
                            CircleShape
                        )
                        .requiredWidth(64.dp)
                        .requiredHeight(64.dp)
                )
            }

            // Mock scroll
            val dishes =
                arrayOf(restaurant.dishes, restaurant.dishes).flatMap { it }

            for (dish in dishes) {
                DishCard(dish, modifier) {
                    restaurantViewModel.onClickedOnDish(dish.id)
                }
            }
        })
}
