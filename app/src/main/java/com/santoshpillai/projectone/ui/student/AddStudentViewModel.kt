package com.santoshpillai.projectone.ui.student

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.santoshpillai.projectone.ui.common.ContactTextFieldState
import com.santoshpillai.projectone.ui.common.RequiredTextFieldState

class AddStudentViewModel : ViewModel() {
    val firstName: RequiredTextFieldState by mutableStateOf(RequiredTextFieldState())
    val lastName: RequiredTextFieldState by mutableStateOf(RequiredTextFieldState())
    val mobileNumber: ContactTextFieldState by mutableStateOf(ContactTextFieldState())

    val availableGenders = listOf<String>("male", "female")
    var gender: String by mutableStateOf("")
    var hasLearnerLicense: Boolean by mutableStateOf(false)
    var hasValidLicense: Boolean by mutableStateOf(false)


    fun onGenderChange(selectedGender: String){
       gender = selectedGender
    }

    fun onHasLearnerLicenseChange(hasLicense: Boolean){
        hasLearnerLicense = hasLicense
    }

    fun onHasValidLicenseChange(hasLicense: Boolean){
        hasValidLicense = hasLicense
    }

}