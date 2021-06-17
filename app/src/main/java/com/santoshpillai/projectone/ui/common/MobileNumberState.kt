package com.santoshpillai.projectone.ui.common

import android.util.Log

val phoneNumberRegex: Regex = "^\\d{10}\$".toRegex()

class ContactTextFieldState : TextFieldState(
    validator = ::contactFieldValidator,
    error = ::contactFieldError
)


fun contactFieldValidator(contact: String): Boolean {
    return phoneNumberRegex.matches(contact)
}

fun contactFieldError(phoneNumber: String) = "Invalid: $phoneNumber. Must be 10 digit phone number"