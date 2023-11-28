package eu.tortitas.deliverydam2.login.data.network

import javax.inject.Inject

class RegisterService @Inject constructor(
    private val registerClient: RegisterClient
) {
    suspend fun login(email: String, password: String, passwordConfirmation: String): Boolean {
        val response = registerClient.register(email, password, passwordConfirmation)
        if (response.isSuccessful) {
            return true
        }

        return false
    }
}