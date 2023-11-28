package eu.tortitas.deliverydam2.login.data

import eu.tortitas.deliverydam2.login.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginService: LoginService
) {
    suspend fun login(email: String, password: String): Boolean =
        loginService.login(email, password)
}