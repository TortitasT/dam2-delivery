package eu.tortitas.deliverydam2.restaurant.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eu.tortitas.deliverydam2.composables.Loader
import eu.tortitas.deliverydam2.composables.layouts.Default

@Composable
fun DishScreen(
    dishViewModel: DishViewModel,
    modifier: Modifier = Modifier,
) {
    val dish = dishViewModel.dish.collectAsState().value

    if (dish == null) {
        Loader()
        return
    }

    Default(onBack = { dishViewModel.onBack() }, actions = {
        IconButton(onClick = { }) {
            Icon(Icons.Outlined.FavoriteBorder, "Add to favorites")
        }
    }, content = {
        AsyncImage(
            model = dish.coverUrl,
            contentDescription = "Dish image",
            modifier = modifier
                .fillMaxWidth()
                .requiredHeight(128.dp)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.tint(
                color = Color.Black
                    .copy(alpha = 0.5f),
                blendMode = BlendMode.Darken,
            )
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(text = dish.name, style = MaterialTheme.typography.titleLarge)

            Text(text = dish.description, style = MaterialTheme.typography.bodyMedium)
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(text = "Ingredients", style = MaterialTheme.typography.titleMedium)

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                dish.ingredients.forEach { ingredient ->
                    Text(text = ingredient, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(text = "Allergens", style = MaterialTheme.typography.titleMedium)

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                dish.allergens.forEach { allergen ->
                    Text(text = allergen, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    })
}
