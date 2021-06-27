package com.santoshpillai.projectone.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.google.android.material.datepicker.MaterialTextInputPicker
import com.santoshpillai.projectone.data.model.Student
import com.santoshpillai.projectone.ui.NavActions
import com.santoshpillai.projectone.ui.common.AppBottomBar
import com.santoshpillai.projectone.ui.state.AppBottomSheetState
import com.santoshpillai.projectone.ui.state.Event
import com.santoshpillai.projectone.ui.state.ToolBarState
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navActions: NavActions,
    homeScreenVm: HomeScreenViewModel,
) {
    val students by homeScreenVm.getStudents.observeAsState()
    val screeState by homeScreenVm.toolBarState.observeAsState()
    val selectedStudents = homeScreenVm.selectedStudents
    val bottomSheet by homeScreenVm.currentBottomSheet.observeAsState()

    HomeScreenContent(
        navActions = navActions,
        students = students,
        selectedStudents = selectedStudents,
        onStudentSelect = homeScreenVm::onStudentSelect,
        onClearSelections = homeScreenVm::onClearSelections,
        onDeleteStudents = homeScreenVm::onStudentDelete,
        screenState = screeState,
        appBottomSheet = bottomSheet,
        showBottomSheet = homeScreenVm::showBottomSheet
    )
}


@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterialApi
@Composable
fun HomeScreenContent(
    navActions: NavActions,
    students: List<Student>?,
    selectedStudents: List<Long>,
    onStudentSelect: (Long) -> Unit,
    onClearSelections: () -> Unit,
    onDeleteStudents: () -> Unit,
    screenState: Event<ToolBarState>?,
    appBottomSheet: Event<AppBottomSheetState>?,
    showBottomSheet: () -> Unit
){

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        if (appBottomSheet?.getContentIfNotHandled() is AppBottomSheetState.HomeScreenBS) {
            bottomSheetScaffoldState.bottomSheetState.expand()
        } else {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
    }

    BottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(MaterialTheme.colors.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Divider(
                        modifier = Modifier
                            .height(8.dp)
                            .width(50.dp)
                            .background(MaterialTheme.colors.primary)
                            .clip(MaterialTheme.shapes.small)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                //onDeleteStudents()
                            }
                            .padding(5.dp),
                    ){
                        Icon(Icons.Filled.Delete, contentDescription = "")
                        Spacer(modifier = Modifier.width(20.dp))
                        Text("Delete", style = MaterialTheme.typography.body2)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            }
                            .padding(5.dp),
                    ){
                        Icon(Icons.Filled.Edit, contentDescription = "")
                        Spacer(modifier = Modifier.width(20.dp))
                        Text("Edit", style = MaterialTheme.typography.body2)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            }
                            .padding(5.dp),
                    ){
                        Icon(Icons.Filled.Message, contentDescription = "")
                        Spacer(modifier = Modifier.width(20.dp))
                        Text("Send Payment Reminder", style = MaterialTheme.typography.body2)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            }
                            .padding(5.dp),
                    ){
                        Icon(Icons.Filled.Favorite, contentDescription = "")
                        Spacer(modifier = Modifier.width(20.dp))
                        Text("Add Favorite", style = MaterialTheme.typography.body2)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            }
                            .padding(5.dp),
                    ){
                        Icon(Icons.Filled.Done, contentDescription = "")
                        Spacer(modifier = Modifier.width(20.dp))
                        Text("Mark Course Complete", style = MaterialTheme.typography.body2)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            }
                            .padding(5.dp),
                    ){
                        Icon(Icons.Filled.Pending, contentDescription = "")
                        Spacer(modifier = Modifier.width(20.dp))
                        Text("Mark Course Pending", style = MaterialTheme.typography.body2)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                            }
                            .padding(5.dp),
                    ){
                        Icon(Icons.Filled.Message, contentDescription = "")
                        Spacer(modifier = Modifier.width(20.dp))
                        Text("Send Message", style = MaterialTheme.typography.body2)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
        },
    ){
        Scaffold(
            topBar = {
                HomeScreenTopBar(
                    state = screenState,
                    onClearSelections = onClearSelections,
                    showBottomSheet = showBottomSheet
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
}

@Composable
fun HomeScreenTopBar(
    state: Event<ToolBarState>?,
    onClearSelections: () -> Unit,
    showBottomSheet: () -> Unit
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
                },
                actions = {
                    HomeScreenMultiSelectActions(
                        showBottomSheet
                    )
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
        Icon(Icons.Filled.AccountCircle, contentDescription = null)
    }
}

@Composable
fun HomeScreenMultiSelectActions(
    showBottomSheet: () -> Unit
){
    IconButton(onClick = {
        showBottomSheet()
    }) {
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