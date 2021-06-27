package com.santoshpillai.projectone.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santoshpillai.projectone.data.local.StudentRepository
import com.santoshpillai.projectone.data.model.Student
import com.santoshpillai.projectone.ui.state.AppBottomSheetState
import com.santoshpillai.projectone.ui.state.Event
import com.santoshpillai.projectone.ui.state.ToolBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val studentRepository: StudentRepository
) : ViewModel() {
    private val _toolBarState = MutableLiveData<Event<ToolBarState>>(Event(ToolBarState.SearchViewState))
    val toolBarState: LiveData<Event<ToolBarState>>
        get() = _toolBarState

    private val _currentBottomSheet = MutableLiveData<Event<AppBottomSheetState>>(null)
    val currentBottomSheet: LiveData<Event<AppBottomSheetState>>
        get() = _currentBottomSheet

    fun showBottomSheet(){
        if (_currentBottomSheet.value is  Event){
            _currentBottomSheet.value = null
        }else {
            _currentBottomSheet.value = Event(AppBottomSheetState.HomeScreenBS())
        }
    }

    // get students from the database
    private var _getStudents: LiveData<List<Student>> = MutableLiveData()
    val getStudents: LiveData<List<Student>>
        get() = _getStudents


    // select students
    // TODO: what is mutableStateOf
    // TODO: Add and remove items to List
    var selectedStudents: List<Long> by mutableStateOf(listOf())
    fun onStudentSelect(id: Long){
        selectedStudents  = if(selectedStudents.contains(id)){
            selectedStudents.toMutableList().also { it.remove(id) }
        } else{
            selectedStudents + listOf(id)
        }

        if (selectedStudents.isNotEmpty()){
            _toolBarState.value = Event(ToolBarState.MultiSelectionState(selectedStudents.size))
        } else {
            _toolBarState.value = Event(ToolBarState.SearchViewState)
        }
    }

    fun onClearSelections(){
        selectedStudents = listOf()
        _toolBarState.value = Event(ToolBarState.SearchViewState)
    }

    // init block
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _getStudents = studentRepository.getAllStudents()
        }
    }

    fun onStudentDelete() {
        println("deleting student with ids $selectedStudents")
        viewModelScope.launch(Dispatchers.IO) {
            for (studentID in selectedStudents) {
                studentRepository.deleteStudent(studentID)
            }
            // update UI state
            //_toolBarState.value = Event(HomeScreenToolBarState.StudentsDeleted(selectedStudents))
        }
    }
}