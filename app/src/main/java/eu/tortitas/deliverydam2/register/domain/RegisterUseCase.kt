package eu.tortitas.deliverydam2.login.domain

import eu.tortitas.deliverydam2.login.data.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        passwordConfirmation: String
    ): Boolean =
        registerRepository.register(email, password, passwordConfirmation)
}