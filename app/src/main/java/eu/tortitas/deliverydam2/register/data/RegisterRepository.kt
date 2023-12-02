package eu.tortitas.deliverydam2.login.data

import android.util.Log
import eu.tortitas.deliverydam2.core.security.HashModule
import eu.tortitas.deliverydam2.login.data.network.RegisterService
import javax.inject.Inject

class RegisterRepository @Inject constructor(
    private val registerService: RegisterService,
    private val hashModule: HashModule
) {
    suspend fun register(email: String, password: String): Boolean {
        val hashedPassword = hashModule.hash(password, hashModule.generateSalt(email))
        Log.i("RegisterRepository", "register: $hashedPassword")
        return registerService.register(email, hashedPassword)
    }
}