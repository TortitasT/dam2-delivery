package eu.tortitas.deliverydam2.register.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordConfirmation by rememberSaveable { mutableStateOf("") }

    Box(modifier = modifier
        .padding(16.dp)
        .fillMaxSize()) {
        Surface(modifier = modifier.padding(PaddingValues(16.dp, 64.dp))) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Column {
                    Text(
                        text = "Get Started",
                        modifier = modifier,
                        style = TextStyle(
                            fontSize = 32.sp
                        )
                    )
                    Text(
                        text = "Start by creating an account",
                        modifier = modifier,
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                }

                TextField(
                    label = { Text(text = "Email") },
                    modifier = modifier.fillMaxWidth(),
                    value = username, onValueChange = { username = it })

                TextField(
                    label = { Text(text = "Password") },
                    modifier = modifier.fillMaxWidth(),
                    value = password, onValueChange = { password = it })

                TextField(
                    label = { Text(text = "Password confirmation") },
                    modifier = modifier.fillMaxWidth(),
                    value = passwordConfirmation, onValueChange = { passwordConfirmation = it })

                TextButton(onClick = onNavigateToLogin){
                    Text(text = "Already have an account? Login!")
                }
            }
        }

        FloatingActionButton(
            modifier = modifier.align(Alignment.BottomEnd),
            onClick = { /*TODO*/ }) {
            Icon(Icons.Outlined.Done, "Login")
        }
    }
}
