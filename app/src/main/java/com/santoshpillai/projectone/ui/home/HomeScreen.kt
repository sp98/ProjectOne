package com.santoshpillai.projectone.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.santoshpillai.projectone.data.model.Student
import com.santoshpillai.projectone.ui.NavActions
import com.santoshpillai.projectone.ui.common.AppBottomBar
import com.santoshpillai.projectone.ui.state.Event
import com.santoshpillai.projectone.ui.state.ToolBarState

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navActions: NavActions,
    homeScreenVm: HomeScreenViewModel,
) {
    val students by homeScreenVm.getStudents.observeAsState()
    val screeState by homeScreenVm.toolBarState.observeAsState()
    val selectedStudents = homeScreenVm.selectedStudents

    HomeScreenContent(
        navActions = navActions,
        students = students,
        selectedStudents = selectedStudents,
        onStudentSelect = homeScreenVm::onStudentSelect,
        onClearSelections = homeScreenVm::onClearSelections,
        screenState = screeState
    )
}


@ExperimentalMaterialApi
@Composable
fun HomeScreenContent(
    navActions: NavActions,
    students: List<Student>?,
    selectedStudents: List<Long>,
    onStudentSelect: (Long) -> Unit,
    onClearSelections: () -> Unit,
    screenState: Event<ToolBarState>?
){
    Scaffold(
        topBar = {
            HomeScreenTopBar(
                state = screenState,
                onClearSelections = onClearSelections
            )
        },
        bottomBar = {
            AppBottomBar(navActions)
        },
        floatingActionButton = { AddStudentButton(navActions) }
    ) {
        // TODO Need to add "androidx.compose.runtime:runtime-livedata:$compose_version" to use ObserveAsState()

        Box(modifier = Modifier.padding(it)) {
            ShowStudents(
                students = students,
                selectedStudents = selectedStudents,
                // TODO: what is :: reference
                onSelect = onStudentSelect,
            )
        }
    }

}

@Composable
fun HomeScreenTopBar(
    state: Event<ToolBarState>?,
    onClearSelections: () -> Unit,
) {
    when (val state = state?.peekContent()) {
        is ToolBarState.MultiSelectionState -> {
            TopAppBar(
                title = { Text("${state.selectedStudents} Selected ") },
                navigationIcon = {
                    IconButton(
                        onClick = { onClearSelections() }
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        }
        is ToolBarState.SearchViewState -> {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "")
                    }
                },
                actions = {
                    HomeScreenActions()
                }
            )
        }
    }

}


@Composable
fun HomeScreenActions() {
    IconButton(
        onClick = { /*TODO*/ }
    ) {
        Icon(Icons.Filled.Search, contentDescription = null)
    }

    IconButton(
        onClick = { /*TODO*/ }
    ) {
        Icon(Icons.Filled.MoreVert, contentDescription = null)
    }
}


@Composable
fun AddStudentButton(navActions: NavActions) {
    FloatingActionButton(
        onClick = { navActions.toAddStudentScreen() },
        shape = CircleShape,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    ) {
        Icon(Icons.Filled.Add, "add")
    }
}


@ExperimentalMaterialApi
@Composable
fun ShowStudents(
    students: List<Student>?,
    selectedStudents: List<Long> = listOf(),
    onSelect: (Long) -> Unit
) {
    Column() {
        if (students != null) {
            LazyColumn {
                items(items = students) { student ->
                    val backgroundColor = if (selectedStudents.contains(student.studentID)) {
                        MaterialTheme.colors.primary.copy(alpha = 0.12f)
                    } else {
                        MaterialTheme.colors.background
                    }
                    ListItem(
                        modifier = Modifier
                            .pointerInput(selectedStudents) {
                                detectTapGestures(
                                    onLongPress = {
                                        onSelect(student.studentID)
                                    },
                                    onTap = {
                                        if (selectedStudents.isNotEmpty()) {
                                            onSelect(student.studentID)
                                        } else {
                                            // TODO: navigate to student detail view
                                        }
                                    }
                                )
                            }
                            .background(backgroundColor),
                        icon = {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = null
                            )
                        },
                        text = { Text(text = "${student.firstName} ${student.lastName}") },
                        secondaryText = { Text(text = "${student.contact}") },
                    )
                    Divider()
                }
            }
        }
    }
}