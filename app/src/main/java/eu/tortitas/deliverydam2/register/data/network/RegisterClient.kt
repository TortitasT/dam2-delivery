package eu.tortitas.deliverydam2.login.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RegisterClient {
    @GET("/v3/cd51b18b-1c89-46df-9425-54651f2f364e")
    suspend fun register(
        @Query("user") user: String,
        @Query("hashed_password") hashedPassword: ByteArray,
    ): Response<RegisterResponse>
}