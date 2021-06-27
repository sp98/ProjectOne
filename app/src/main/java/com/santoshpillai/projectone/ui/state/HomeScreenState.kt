package com.santoshpillai.projectone.ui.state

import com.santoshpillai.projectone.data.model.Student

sealed class ToolBarState {
    /*
    Home Screen can have following states:
    1. Default State.
    2. Selected Students State
    3. Deleted Students State
     */
    data class MultiSelectionState(val students: List<Student>) : ToolBarState()
    object SearchViewState : ToolBarState()
}