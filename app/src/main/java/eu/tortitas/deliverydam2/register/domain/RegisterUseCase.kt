package eu.tortitas.deliverydam2.login.domain

import eu.tortitas.deliverydam2.core.security.HashModule
import eu.tortitas.deliverydam2.login.data.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository,
    private val hashModule: HashModule
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        passwordConfirmation: String
    ): Boolean {
        if (email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }

        if (password.length < 8) {
            return false
        }

        if (password != passwordConfirmation) {
            return false
        }

        val hashedPassword = hashModule.hash(password, hashModule.generateSalt())
        return registerRepository.register(email, hashedPassword)
    }
}