package eu.tortitas.deliverydam2.core.security

import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.inject.Inject

class HashModule @Inject constructor() {
    private val random = SecureRandom()

    fun generateSalt(field: String): ByteArray {
        return field.toByteArray()
    }

    fun isExpectedPassword(password: String, salt: ByteArray, expectedHash: String): Boolean {
        val pwdHash = hash(password, salt)
        if (pwdHash.length != expectedHash.length) return false
        return pwdHash.indices.all { pwdHash[it] == expectedHash[it] }
    }

    fun hash(
        password: String, salt: ByteArray
    ): String {
        val spec = PBEKeySpec(
            password.toCharArray(), salt, 1000, 256
        )
        try {
            val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            return skf.generateSecret(spec).encoded.toString(charset("UTF-8"))
        } catch (e: NoSuchAlgorithmException) {
            throw AssertionError("Error while hashing a password: " + e.message, e)
        } catch (e: InvalidKeySpecException) {
            throw AssertionError("Error while hashing a password: " + e.message, e)
        } finally {
            spec.clearPassword()
        }
    }
}