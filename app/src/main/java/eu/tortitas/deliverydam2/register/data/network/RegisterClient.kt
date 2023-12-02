package eu.tortitas.deliverydam2.login.data.network

import eu.tortitas.deliverydam2.register.data.network.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterClient {
    @POST("/auth/v1/signup")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<RegisterResponse>
}