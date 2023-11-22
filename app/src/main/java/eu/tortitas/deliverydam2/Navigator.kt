package eu.tortitas.deliverydam2

import android.annotation.SuppressLint
import androidx.navigation.NavHostController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object Navigator {
    @SuppressLint("StaticFieldLeak")
    private var navController: NavHostController? = null

    fun setController(controller: NavHostController) {
        navController = controller
    }

    fun clear() {
        navController = null
    }

    fun navigate(route: String) {
        navController?.navigate(route)
    }

    @Provides
    fun provideNavigator(): Navigator {
        return this
    }
}
