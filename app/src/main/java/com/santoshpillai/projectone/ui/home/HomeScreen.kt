package com.santoshpillai.projectone.ui.home

import android.graphics.drawable.Icon
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.santoshpillai.projectone.ui.NavActions
import com.santoshpillai.projectone.ui.common.AppBottomBar

@Composable
fun HomeScreen(
    navActions: NavActions
){
    Scaffold(
        topBar = {},
        bottomBar = {
            AppBottomBar(navActions)
        },
        floatingActionButton = { AddStudentButton(navActions)}
    ){
        // display student list
    }
}


@Composable
fun AddStudentButton(navActions: NavActions){
    FloatingActionButton(
        onClick = { navActions.toAddStudentScreen()},
        shape = CircleShape,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    ) {
        Icon(Icons.Filled.Add, "add" )
    }
}