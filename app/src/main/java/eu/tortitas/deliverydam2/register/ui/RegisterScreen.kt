package eu.tortitas.deliverydam2.register.ui

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
import androidx.compose.material3.AlertDialog
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
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
    modifier: Modifier = Modifier
) {
    val email: String by registerViewModel.email.collectAsState()
    val emailError: String = registerViewModel.emailError.collectAsState("").value ?: ""
    val emailModified: Boolean = registerViewModel.emailModified.collectAsState().value

    val password: String by registerViewModel.password.collectAsState()
    val passwordError: String = registerViewModel.passwordError.collectAsState("").value ?: ""
    val passwordModified: Boolean = registerViewModel.passwordModified.collectAsState().value

    val passwordConfirmation: String by registerViewModel.passwordConfirmation.collectAsState()
    val passwordConfirmationError: String =
        registerViewModel.passwordConfirmationError.collectAsState("").value ?: ""
    val passwordConfirmationModified: Boolean =
        registerViewModel.passwordConfirmationModified.collectAsState().value

    val loading: Boolean by registerViewModel.loading.collectAsState()

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
                    value = email,
                    enabled = !loading,
                    onValueChange = {
                        registerViewModel.onEmailOrPasswordChanged(
                            it,
                            password,
                            passwordConfirmation
                        )
                    })

                if (emailError.isNotEmpty() && emailModified) {
                    Text(
                        text = emailError,
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                TextField(
                    label = { Text(text = "Password") },
                    modifier = modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    value = password,
                    enabled = !loading,
                    onValueChange = {
                        registerViewModel.onEmailOrPasswordChanged(
                            email,
                            it,
                            passwordConfirmation
                        )
                    })

                if (passwordError.isNotEmpty() && passwordModified) {
                    Text(
                        text = passwordError,
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                TextField(
                    label = { Text(text = "Password confirmation") },
                    modifier = modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    value = passwordConfirmation,
                    enabled = !loading,
                    onValueChange = {
                        registerViewModel.onEmailOrPasswordChanged(
                            email,
                            password,
                            it
                        )
                    })

                if (passwordConfirmationError.isNotEmpty() && passwordConfirmationModified) {
                    Text(
                        text = passwordConfirmationError,
                        modifier = modifier,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                TextButton(onClick = { registerViewModel.onNavigateToLogin() }) {
                    Text(text = "Already have an account? Login!")
                }
            }
        }

        FloatingActionButton(
            modifier = modifier.align(Alignment.BottomEnd),
            onClick = {
                registerViewModel.onRegister()
            }) {
            Icon(Icons.Outlined.Done, "Login")
        }

        if (registerViewModel.popupShown.collectAsState().value) {
            AlertDialog(
                onDismissRequest = { registerViewModel.onPopupDismissed() },
                confirmButton = { /*TODO*/ },
                title = {
                    Text(text = "Uh oh!")
                },
                text = {
                    Text(text = registerViewModel.popupText.collectAsState().value)
                },
            )
        }
    }
}
