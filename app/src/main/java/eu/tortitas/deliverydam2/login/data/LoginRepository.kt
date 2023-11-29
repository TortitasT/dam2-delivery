package eu.tortitas.deliverydam2.login.data

import eu.tortitas.deliverydam2.login.data.network.LoginResponse
import eu.tortitas.deliverydam2.login.data.network.LoginService
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginService: LoginService
) {
    suspend fun login(email: String, password: String): Response<LoginResponse> =
        loginService.login(email, password)
}