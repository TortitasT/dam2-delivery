package eu.tortitas.deliverydam2.login.data.network

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("role")
    val role: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("phone")
    val phone: String,
)
