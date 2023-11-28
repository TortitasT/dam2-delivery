package eu.tortitas.deliverydam2.login.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginClient {
    @GET("/v3/cd51b18b-1c89-46df-9425-54651f2f364e")
    suspend fun doLogin(
        @Query("user") user: String,
        @Query("password") password: String
    ): Response<LoginResponse>
}