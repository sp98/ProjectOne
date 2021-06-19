package com.santoshpillai.projectone.ui.common

const val paymentRegex = "^\\d{3,5}\$"

class PaidAmountState: TextFieldState(
    validator = ::paidAmountValidator,
    error = ::paidAmountError
) {
}

fun paidAmountValidator(amount: String): Boolean = paymentRegex.toRegex().matches(amount)

fun paidAmountError(amount: String):String = "Invalid amount: $amount. Must be greater than 100"
