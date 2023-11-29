package eu.tortitas.deliverydam2.login.domain

data class LoginHadErrors(
    val errorsOnInput: Boolean = false,
    val errorsOnServer: Boolean = false
) {
    val hadErrors: Boolean
        get() = errorsOnInput || errorsOnServer
}