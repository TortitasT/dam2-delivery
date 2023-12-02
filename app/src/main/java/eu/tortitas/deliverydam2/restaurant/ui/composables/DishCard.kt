package eu.tortitas.deliverydam2.restaurant.ui.composables

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import eu.tortitas.deliverydam2.restaurant.domain.Dish
import java.security.MessageDigest

// TODO: Esto es para tener un precio que pintar y que
// siempre sea el mismo para cada producto pero no tiene
// ningún sentido en la vida real claro ^^
fun getDishPrice(dish: Dish): String {
    val price = MessageDigest.getInstance("MD5").digest(dish.description.toByteArray())
        .fold(0) { acc, byte -> acc + byte.toInt() }
        .toDouble() / 100

    return "$price €"
}

@Composable
fun DishCard(
    dish: Dish,
    modifier: Modifier = Modifier,
    onClickOnCard: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                Log.i("DishCard", "Clicked on dish ${dish.name}")
                onClickOnCard()
            }
    )
    {
        Row(
            modifier = modifier
                .padding(14.dp)
                .height(100.dp)
        ) {
            AsyncImage(
                model = dish.coverUrl,
                contentDescription = dish.name,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .requiredWidth(100.dp)
                    .requiredHeight(100.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = modifier.requiredWidth(16.dp))

            Column(
                modifier = modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = dish.name,
                    modifier = modifier
                )

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = getDishPrice(dish),
                        modifier = modifier
                    )

                    QuantityButtons(
                        modifier = modifier,
                        onClickPlus = { },
                        onClickMinus = { }
                    )
                }
            }
        }
    }
}

@Composable
fun QuantityButtons(
    modifier: Modifier = Modifier,
    onClickPlus: () -> Unit,
    onClickMinus: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.background)
    )
    {
        IconButton(
            onClick = onClickMinus,
            modifier = modifier
        )
        {
            Icon(
                Icons.Outlined.Delete, "Remove",
                modifier = modifier
                    .size(20.dp)
            )
        }

        Text(text = "0", modifier = modifier.padding(8.dp))

        IconButton(
            onClick = onClickPlus,
            modifier = modifier
        )
        {
            Icon(Icons.Outlined.Add, "Add to cart")
        }
    }
}