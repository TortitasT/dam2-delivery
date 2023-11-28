package eu.tortitas.deliverydam2.login.data.network

import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginClient: LoginClient
) {
    suspend fun login(email: String, password: String): Boolean {
        val response = loginClient.doLogin(email, password)
        if (response.isSuccessful) {
            return true
        }

        return false
    }
}