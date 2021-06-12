package com.santoshpillai.projectone.ui.home

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
        }
    ){
        // display student list
    }

}