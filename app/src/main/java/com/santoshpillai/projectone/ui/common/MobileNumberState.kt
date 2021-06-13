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

fun contactFieldError(contact: String) = "invalid contact: $contact"