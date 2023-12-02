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
import eu.tortitas.deliverydam2.login.ui.LoginScreen
import eu.tortitas.deliverydam2.login.ui.LoginViewModel
import eu.tortitas.deliverydam2.register.ui.RegisterScreen
import eu.tortitas.deliverydam2.register.ui.RegisterViewModel
import eu.tortitas.deliverydam2.restaurant.ui.DishScreen
import eu.tortitas.deliverydam2.restaurant.ui.DishViewModel
import eu.tortitas.deliverydam2.restaurant.ui.RestaurantScreen
import eu.tortitas.deliverydam2.restaurant.ui.RestaurantViewModel
import eu.tortitas.deliverydam2.ui.theme.DeliveryDAM2Theme
import javax.inject.Inject

@HiltAndroidApp
class DeliveryApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()
    private val registerViewModel: RegisterViewModel by viewModels()
    private val restaurantViewModel: RestaurantViewModel by viewModels()
    private val dishViewModel: DishViewModel by viewModels()

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
                    NavigationHost(
                        loginViewModel,
                        registerViewModel,
                        restaurantViewModel,
                        dishViewModel,
                        navigator
                    )
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
    restaurantViewModel: RestaurantViewModel,
    dishViewModel: DishViewModel,
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

        composable("restaurant/{restaurantId}") {
            val restaurantId = it.arguments?.getString("restaurantId") ?: return@composable

            restaurantViewModel.loadRestaurant(Integer.parseInt(restaurantId))
            RestaurantScreen(restaurantViewModel, modifier)
        }

        composable("dish/{dishId}") {
            val dishId = it.arguments?.getString("dishId") ?: return@composable

            dishViewModel.loadDish(Integer.parseInt(dishId))
            DishScreen(dishViewModel, modifier)
        }
    }
}

