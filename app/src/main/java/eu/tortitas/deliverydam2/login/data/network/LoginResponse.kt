package eu.tortitas.deliverydam2.login.data.network

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("token_type")
    val tokenType: String,

    @SerializedName("expires_in")
    val expiresIn: Int,

    @SerializedName("expires_at")
    val expiresAt: Int,

    @SerializedName("refresh_token")
    val refreshToken: String,
)
