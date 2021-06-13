package com.santoshpillai.projectone.ui.common

class RequiredTextFieldState : TextFieldState(
    validator = ::requiredFieldValidator,
    error = ::requiredFieldError
)

fun requiredFieldValidator(text: String): Boolean {
    return text.isNotEmpty()
}

fun requiredFieldError(text: String) = "required *"
