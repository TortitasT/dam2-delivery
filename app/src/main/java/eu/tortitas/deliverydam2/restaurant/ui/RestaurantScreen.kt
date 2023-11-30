package eu.tortitas.deliverydam2.restaurant.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eu.tortitas.deliverydam2.composables.Header
import eu.tortitas.deliverydam2.restaurant.ui.composables.DishCard


@Composable
fun RestaurantScreen(
    restaurantViewModel: RestaurantViewModel,
    modifier: Modifier = Modifier,
) {
    val restaurant = restaurantViewModel.restaurant.collectAsState().value

    if (restaurant == null) {
        restaurantViewModel.loadRestaurant(1)
    }

    Header(onBack = { restaurantViewModel.onLogout() }) {
        IconButton(onClick = { }) {
            Icon(Icons.Outlined.FavoriteBorder, "Add to favorites")
        }
    }

    Box(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Surface(modifier = modifier.padding(PaddingValues(16.dp, 64.dp))) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(0.dp, 20.dp, 0.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                )
                {
                    Text(
                        text = restaurant?.name ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .widthIn(0.dp, 300.dp)
                    )

                    AsyncImage(
                        model = restaurant?.coverUrl ?: "",
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
                    arrayOf(restaurant?.dishes, restaurant?.dishes).flatMap { it ?: emptyList() }

                for (dish in dishes) {
                    DishCard(dish, modifier, {})
                }
            }
        }
    }
}
