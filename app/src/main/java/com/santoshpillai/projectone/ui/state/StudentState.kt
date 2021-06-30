package com.santoshpillai.projectone.ui.state

import com.santoshpillai.projectone.data.model.Student

sealed class StudentState {
    data class Success(val students: List<Student>?) : StudentState()

    // TODO Learn about nothing class in kotlin
    data class InProgress(val nothing: Nothing? = null) : StudentState()
    data class Error(val errMessage: String) : StudentState()
}