package eu.tortitas.deliverydam2.core.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eu.tortitas.deliverydam2.BuildConfig
import eu.tortitas.deliverydam2.login.data.network.LoginClient
import eu.tortitas.deliverydam2.login.data.network.RegisterClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class NetworkClient {
    lateinit var retrofit: Retrofit

    init {
        buildRetrofit(null)
    }

    fun setToken(token: String) {
        buildRetrofit(token)
    }

    private fun buildRetrofit(token: String?) {
        val client = okhttp3.OkHttpClient.Builder()
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.addHeader("apikey", BuildConfig.SUPABASE_API_KEY)
                if (token != null) {
                    requestBuilder.addHeader("Authorization", "Bearer $token")
                }
                chain.proceed(requestBuilder.build())
            }.build()

        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://clutrtqceergafdtqjeb.supabase.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
class Retrofit {
    @Singleton
    @Provides
    fun provideNetworkClient(): NetworkClient = NetworkClient()

    @Provides
    fun provideLoginClient(
        networkClient: NetworkClient
    ): LoginClient =
        networkClient.retrofit.create(LoginClient::class.java)

    @Provides
    fun provideRegisterClient(
        networkClient: NetworkClient
    ): RegisterClient =
        networkClient.retrofit.create(RegisterClient::class.java)
}