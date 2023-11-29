package eu.tortitas.deliverydam2.login.data

import eu.tortitas.deliverydam2.login.data.network.RegisterService
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val registerService: RegisterService
) {
    suspend fun register(email: String, hashedPassword: ByteArray): Boolean =
        registerService.register(email, hashedPassword)
}