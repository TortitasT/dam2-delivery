package eu.tortitas.deliverydam2.composables.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import eu.tortitas.deliverydam2.composables.Header

@Composable
fun Default(
    onBack: () -> Unit,
    actions: @Composable () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Header(onBack = onBack, actions = actions)
        Box(
            modifier = modifier
                .padding(8.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Surface(modifier = modifier.padding(PaddingValues(16.dp, 0.dp, 16.dp, 16.dp))) {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    content()
                }
            }
        }
    }
}