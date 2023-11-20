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
    @SuppressLint("StaticFieldLeak") // hmm
    private lateinit var navHostController: NavHostController

    fun setNavHostController(navHostController: NavHostController) {
        this.navHostController = navHostController
    }

    @Provides
    fun provideNavHostController(): NavHostController {
        return navHostController
    }
}