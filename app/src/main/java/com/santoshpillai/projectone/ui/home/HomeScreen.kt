package com.santoshpillai.projectone.ui.home

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
import androidx.compose.ui.unit.dp
import com.santoshpillai.projectone.data.model.Student
import com.santoshpillai.projectone.ui.NavActions
import com.santoshpillai.projectone.ui.common.AppBottomBar

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navActions: NavActions,
    homeScreenVm: HomeScreenViewModel,
) {
    val students by homeScreenVm.students.observeAsState()

    Scaffold(
        topBar = {HomeScreenTopBar()},
        bottomBar = {
           AppBottomBar(navActions)
        },
        floatingActionButton = { AddStudentButton(navActions) }
    ) {
        // TODO Need to add "androidx.compose.runtime:runtime-livedata:$compose_version" to use ObserveAsState()

        Box(modifier = Modifier.padding(it)){
            ShowStudents(students)
        }
    }
}


@Composable
fun HomeScreenTopBar(){
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


@Composable
fun HomeScreenActions(){
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
    students: List<Student>?
){
    Column() {
        if (students != null){
        LazyColumn {
            items(items = students) { student ->
                ListItem(
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