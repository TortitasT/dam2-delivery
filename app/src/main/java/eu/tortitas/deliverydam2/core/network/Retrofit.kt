package eu.tortitas.deliverydam2.core.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.tortitas.deliverydam2.login.data.network.LoginClient
import eu.tortitas.deliverydam2.login.data.network.RegisterClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Retrofit {
    @Singleton
    @Provides
    fun provideRetrofit(): retrofit2.Retrofit =
        retrofit2.Retrofit.Builder().baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    @Singleton
    @Provides
    fun provideLoginClient(retrofit: retrofit2.Retrofit): LoginClient =
        retrofit.create(LoginClient::class.java)

    @Singleton
    @Provides
    fun provideRegisterClient(retrofit: retrofit2.Retrofit): RegisterClient =
        retrofit.create(RegisterClient::class.java)
}