package com.santoshpillai.projectone.ui.student

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.santoshpillai.projectone.ui.common.RequiredTextFieldState

class AddStudentViewModel: ViewModel() {

    val firstName : RequiredTextFieldState by mutableStateOf(RequiredTextFieldState())
    val lastName : RequiredTextFieldState by mutableStateOf(RequiredTextFieldState())

}