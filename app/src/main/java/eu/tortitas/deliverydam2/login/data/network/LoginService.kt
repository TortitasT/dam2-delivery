package eu.tortitas.deliverydam2.login.data.network

import retrofit2.Response
import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginClient: LoginClient
) {
    suspend fun login(email: String, hashedPassword: String): Response<LoginResponse> =
        loginClient.login(LoginRequest(email, hashedPassword))
}