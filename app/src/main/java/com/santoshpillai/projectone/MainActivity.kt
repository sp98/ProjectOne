package com.santoshpillai.projectone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.santoshpillai.projectone.ui.NavGraph
import com.santoshpillai.projectone.ui.home.HomeScreenViewModel
import com.santoshpillai.projectone.ui.student.AddStudentViewModel
import com.santoshpillai.projectone.ui.theme.ProjectOneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val addStudentVM by viewModels<AddStudentViewModel>()
        val homeScreenVM by viewModels<HomeScreenViewModel>()
        setContent {
            ProjectOneTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ProjectOneApp(
                        homeScreenVM,
                        addStudentVM
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun ProjectOneApp(
    homeScreenViewModel: HomeScreenViewModel,
    addStudentViewModel: AddStudentViewModel
) {
    NavGraph(
        homeScreenViewModel,
        addStudentViewModel
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectOneTheme {
    }
}