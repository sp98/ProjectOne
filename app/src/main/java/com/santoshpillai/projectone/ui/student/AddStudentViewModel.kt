package com.santoshpillai.projectone.ui.student

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.santoshpillai.projectone.ui.common.ContactTextFieldState
import com.santoshpillai.projectone.ui.common.PaidAmountState
import com.santoshpillai.projectone.ui.common.RequiredTextFieldState

class AddStudentViewModel : ViewModel() {
    var firstName: RequiredTextFieldState by mutableStateOf(RequiredTextFieldState())
    var lastName: RequiredTextFieldState by mutableStateOf(RequiredTextFieldState())
    var mobileNumber: ContactTextFieldState by mutableStateOf(ContactTextFieldState())
    var paidAmount: PaidAmountState by mutableStateOf(PaidAmountState())

    val availableGenders = listOf<String>("MALE", "FEMALE")
    var gender: String by mutableStateOf("")

    val availableLicenseTypes = listOf("LEARNER'S", "REGULAR")
    var licenseType: String by mutableStateOf("")

    val paymentStatusTypes = listOf("COMPLETE", "PARTIAL")
    var paymentStatus: String by mutableStateOf("")

    fun onGenderChange(selectedGender: String){
       gender = selectedGender
    }

    fun onLicenseTypeChange(type: String){
        licenseType = type
    }

    fun onPaymentStatusChange(status: String){
        paymentStatus = status
    }

    fun showAddButton(): Boolean{
        return when {
            firstName.text.isEmpty() -> false
            lastName.text.isEmpty() -> false
            !mobileNumber.isValid -> false
            gender.isEmpty() -> false
            licenseType.isEmpty() -> false
            paymentStatus.isEmpty() -> false
            paymentStatus.equals("PARTIAL", true) && !paidAmount.isValid -> false
            else -> true
        }
    }

    fun reset() {
        firstName = RequiredTextFieldState()
        lastName = RequiredTextFieldState()
        mobileNumber= ContactTextFieldState()
        paidAmount = PaidAmountState()
        gender = ""
        licenseType = ""
        paymentStatus = ""
    }
}