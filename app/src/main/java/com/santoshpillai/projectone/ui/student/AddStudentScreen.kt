package com.santoshpillai.projectone.ui.student

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.santoshpillai.projectone.ui.NavActions
import com.santoshpillai.projectone.R


@Composable
fun AddStudentScreen(navActions: NavActions){
    Scaffold(
        topBar = { AddStudentTopAppBar(navActions)},
        bottomBar = {},
    ){
        // display student list
    }
}

@Composable
fun AddStudentTopAppBar(navActions: NavActions){
    TopAppBar(
        title = { Text(stringResource(R.string.add_student_screen_title)) },
        navigationIcon = {
            IconButton(
                onClick = { navActions.toHomeScreen() }
            ) {
                Icon(Icons.Filled.ArrowBack, stringResource(R.string.back))
            }
        }
    )
}