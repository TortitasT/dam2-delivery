package eu.tortitas.deliverydam2

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import eu.tortitas.deliverydam2.login.ui.LoginScreen
import eu.tortitas.deliverydam2.register.ui.RegisterScreen
import eu.tortitas.deliverydam2.ui.theme.DeliveryDAM2Theme

@HiltAndroidApp
class DeliveryApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryDAM2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHost()
                }
            }
        }
    }
}

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login") {
            LoginScreen(onNavigateToRegister = {
                navController.navigate("register")
            })
        }

        composable("register") {
            RegisterScreen(onNavigateToLogin = {
                navController.navigate("login")
            })
        }
    }
}

