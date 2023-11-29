package eu.tortitas.deliverydam2.login.data.network

import javax.inject.Inject

class RegisterService @Inject constructor(
    private val registerClient: RegisterClient
) {
    suspend fun register(email: String, hashedPassword: ByteArray): Boolean {
        val response = registerClient.register(email, hashedPassword)
        if (response.isSuccessful) {
            return true
        }

        return false
    }
}