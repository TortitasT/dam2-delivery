package eu.tortitas.deliverydam2.login.domain

import eu.tortitas.deliverydam2.login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean =
        loginRepository.login(email, password)
}