package com.santoshpillai.projectone.ui.home

import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santoshpillai.projectone.data.local.StudentDAO
import com.santoshpillai.projectone.data.local.StudentRepository
import com.santoshpillai.projectone.data.model.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val studentRepository: StudentRepository
): ViewModel() {

    lateinit var students: LiveData<List<Student>>

    init {
        viewModelScope.launch(Dispatchers.IO){
            students = studentRepository.getAllStudents()
        }
    }

}