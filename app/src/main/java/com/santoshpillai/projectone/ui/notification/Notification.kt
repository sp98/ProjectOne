package com.santoshpillai.projectone.ui.notification

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.santoshpillai.projectone.ui.NavActions
import com.santoshpillai.projectone.ui.common.AppBottomBar

@Composable
fun Notification(navActions: NavActions) {
    Scaffold(
        topBar = {},
        bottomBar = {
            AppBottomBar(navActions)
        }
    ) {
        // display student list
    }
}