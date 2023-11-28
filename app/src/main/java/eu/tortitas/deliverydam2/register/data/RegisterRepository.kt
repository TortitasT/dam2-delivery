package eu.tortitas.deliverydam2.login.data

import eu.tortitas.deliverydam2.login.data.network.RegisterService
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val registerService: RegisterService
) {
    suspend fun register(email: String, password: String, passwordConfirmation: String): Boolean =
        registerService.login(email, password, passwordConfirmation)
}