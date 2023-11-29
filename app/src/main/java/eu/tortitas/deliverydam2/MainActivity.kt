package eu.tortitas.deliverydam2

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import eu.tortitas.deliverydam2.core.navigation.Navigator
import eu.tortitas.deliverydam2.dishes.ui.DishesScreen
import eu.tortitas.deliverydam2.login.ui.LoginScreen
import eu.tortitas.deliverydam2.login.ui.LoginViewModel
import eu.tortitas.deliverydam2.register.ui.RegisterScreen
import eu.tortitas.deliverydam2.register.ui.RegisterViewModel
import eu.tortitas.deliverydam2.ui.theme.DeliveryDAM2Theme
import javax.inject.Inject

@HiltAndroidApp
class DeliveryApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeliveryDAM2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHost(loginViewModel, registerViewModel, navigator)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.navController = null
    }
}

@Composable
fun NavigationHost(
    loginViewModel: LoginViewModel,
    registerViewModel: RegisterViewModel,
    navigator: Navigator,
    modifier: Modifier = Modifier,
    startDestination: String = "login",
) {
    val navController = rememberNavController()
    navigator.navController = navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login") {
            LoginScreen(loginViewModel, modifier)
        }

        composable("register") {
            RegisterScreen(registerViewModel, modifier)
        }

        composable("dishes") {
            DishesScreen(modifier)
        }
    }
}

