package eu.tortitas.deliverydam2.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LoginScreen(
    loginViewModel: LoginViewModel,
    modifier: Modifier = Modifier,
) {
    val email: String by loginViewModel.email.collectAsState()
    val password: String by loginViewModel.password.collectAsState()
    val loading: Boolean by loginViewModel.loading.collectAsState()

    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Surface(modifier = modifier.padding(PaddingValues(16.dp, 64.dp))) {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Column {
                    Text(
                        text = "Hello there!",
                        modifier = modifier,
                        style = TextStyle(
                            fontSize = 32.sp
                        )
                    )
                    Text(
                        text = "Welcome back",
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
                    value = email,
                    enabled = !loading,
                    onValueChange = { loginViewModel.onEmailOrPasswordChanged(it, password) })

                TextField(
                    label = { Text(text = "Password") },
                    modifier = modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    value = password,
                    enabled = !loading,
                    onValueChange = { loginViewModel.onEmailOrPasswordChanged(email, it) })

                TextButton(onClick = { loginViewModel.onNavigateToRegister() }) {
                    Text(text = "Don’t have an account? Register now!")
                }
            }
        }

        FloatingActionButton(
            modifier = modifier.align(Alignment.BottomEnd),
            onClick = {
                if (loading) {
                    return@FloatingActionButton
                }
                loginViewModel.onLogin()
            }) {
            Icon(Icons.Outlined.Done, "Login")
        }
    }
}
