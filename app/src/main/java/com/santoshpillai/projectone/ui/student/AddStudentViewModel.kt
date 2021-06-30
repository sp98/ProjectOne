package com.santoshpillai.projectone.ui.student

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.santoshpillai.projectone.data.local.StudentRepository
import com.santoshpillai.projectone.data.model.Student
import com.santoshpillai.projectone.ui.common.ContactTextFieldState
import com.santoshpillai.projectone.ui.common.PaidAmountState
import com.santoshpillai.projectone.ui.common.RequiredTextFieldState
import com.santoshpillai.projectone.ui.state.Event
import com.santoshpillai.projectone.ui.state.StudentState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AddStudentViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {

    private var _addStudentResult: LiveData<Event<StudentState>> = MutableLiveData()
    val addStudentResult: LiveData<Event<StudentState>>
        get() = _addStudentResult

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

    fun onGenderChange(selectedGender: String) {
        gender = selectedGender
    }

    fun onLicenseTypeChange(type: String) {
        licenseType = type
    }

    fun onPaymentStatusChange(status: String) {
        paymentStatus = status
    }

    fun showAddButton(): Boolean {
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
        mobileNumber = ContactTextFieldState()
        paidAmount = PaidAmountState()
        gender = ""
        licenseType = ""
        paymentStatus = ""
    }

    fun addStudent() {
        val newStudent = Student(
            firstName = firstName.text.trim(),
            lastName = lastName.text.trim(),
            contact = mobileNumber.text.trim(),
            gender = gender,
            licenseType = licenseType,
            paymentStatus = paymentStatus,
            paidAmount = getPaidAmount(paidAmount)
        )

        // TODO : How to use viewModelScope and update the _addStudentResult here
        // TODO: learn about liveData and coroutines
        _addStudentResult = liveData(Dispatchers.IO) {
            try {
                emit(Event(StudentState.InProgress()))
                studentRepository.insertNewStudent(newStudent)
                emit(Event(StudentState.Success(listOf(newStudent))))

            } catch (e: Exception) {
                emit(Event(StudentState.Error(e.message.toString())))
            }
        }
    }


    private fun getPaidAmount(amount: PaidAmountState): Long {
        if (amount.text != "") {
            return amount.text.trim().toLong()
        }
        return 0
    }
}