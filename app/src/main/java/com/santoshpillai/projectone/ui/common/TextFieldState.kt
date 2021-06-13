package com.santoshpillai.projectone.ui.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

open class TextFieldState(
    private val validator: (String) -> Boolean = { true },
    private val error: (String) -> String = { "" }
) {
    var text: String by mutableStateOf("")

    var isFocused: Boolean by mutableStateOf(false)
    var isFocusedDirty: Boolean by mutableStateOf(false)
    var displayErrors: Boolean by mutableStateOf(false)

    open val isValid: Boolean
        get() = validator(text)

    fun onFocusChange(focused: Boolean) {
        isFocused = focused
        if (focused) isFocusedDirty = true
    }

    fun enableShowErrors() {
        if (isFocusedDirty) {
            displayErrors = true
        }
    }

    fun showErrors() = !isValid && displayErrors

    fun getError(): String? {
        if (showErrors()) {
            return error(text)
        }
        return null
    }
}