package com.santoshpillai.projectone.ui.home

import androidx.lifecycle.*
import com.santoshpillai.projectone.data.local.StudentRepository
import com.santoshpillai.projectone.data.model.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {


    private var _getStudents: LiveData<List<Student>> = MutableLiveData()
    val getStudents: LiveData<List<Student>>
        get() = _getStudents

    init {
        // TODO: Understand liveData Scope
        viewModelScope.launch(Dispatchers.IO) {
            _getStudents = studentRepository.getAllStudents()
        }
    }
}