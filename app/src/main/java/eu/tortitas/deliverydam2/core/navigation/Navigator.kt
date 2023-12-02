package eu.tortitas.deliverydam2.core.navigation

import androidx.navigation.NavHostController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class Navigator {
    var navController: NavHostController? = null

    fun navigate(route: String) {
        navController?.navigate(route)
    }

    fun back() {
        navController?.popBackStack()
    }
}


@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return Navigator()
    }
}
