package eu.tortitas.deliverydam2.login.domain

import eu.tortitas.deliverydam2.core.network.NetworkClient
import eu.tortitas.deliverydam2.login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val networkClient: NetworkClient
) {
    suspend operator fun invoke(email: String, password: String): LoginHadErrors {
        val response = loginRepository.login(email, password)

        if (response.isSuccessful) {
            val token = response.body()?.accessToken
            if (token != null) {
                networkClient.setToken(token)
                return LoginHadErrors()
            }
        }

        return LoginHadErrors(
            errorsOnInput = response.code() in 400..499,
            errorsOnServer = response.code() >= 500 || response.code() == 429 // Too many requests
        )
    }
}