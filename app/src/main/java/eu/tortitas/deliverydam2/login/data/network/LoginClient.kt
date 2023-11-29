package eu.tortitas.deliverydam2.login.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginClient {
    @POST("/auth/v1/token?grant_type=password")
    suspend fun login(
        @Body loginRequest: LoginRequest,
    ): Response<LoginResponse>
}