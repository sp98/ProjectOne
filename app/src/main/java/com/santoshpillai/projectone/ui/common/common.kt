package com.santoshpillai.projectone.ui.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun FormLabel(label:String){
    Text(
        text = label,
        style = MaterialTheme.typography.caption
    )
}

@Composable
fun ScreenTitle(title:String){
    Text(
        text = title,
        style = MaterialTheme.typography.h6
    )
}