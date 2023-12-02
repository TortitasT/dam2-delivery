package eu.tortitas.deliverydam2.login.data

import android.util.Log
import eu.tortitas.deliverydam2.core.security.HashModule
import eu.tortitas.deliverydam2.login.data.network.LoginResponse
import eu.tortitas.deliverydam2.login.data.network.LoginService
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginService: LoginService,
    private val hashModule: HashModule
) {
    suspend fun login(email: String, password: String): Response<LoginResponse> {
        val hashedPassword = hashModule.hash(password, hashModule.generateSalt(email))
        Log.i("LoginRepository", "login: $hashedPassword")
        return loginService.login(email, hashedPassword)
    }
}