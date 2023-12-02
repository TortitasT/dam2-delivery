package eu.tortitas.deliverydam2.login.data.network

import eu.tortitas.deliverydam2.register.data.network.RegisterRequest
import javax.inject.Inject

class RegisterService @Inject constructor(
    private val registerClient: RegisterClient
) {
    suspend fun register(email: String, hashedPassword: String): Boolean {
        val response = registerClient.register(
            RegisterRequest(
                email,
                hashedPassword
            )
        )

        if (response.isSuccessful) {
            return true
        }

        return false
    }
}