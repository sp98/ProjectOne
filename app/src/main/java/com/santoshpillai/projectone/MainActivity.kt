package com.santoshpillai.projectone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.santoshpillai.projectone.ui.NavGraph
import com.santoshpillai.projectone.ui.home.HomeScreen
import com.santoshpillai.projectone.ui.student.AddStudentViewModel
import com.santoshpillai.projectone.ui.theme.ProjectOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val addStudentVM by viewModels<AddStudentViewModel>()
        setContent {
            ProjectOneTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ProjectOneApp(addStudentVM)
                }
            }
        }
    }
}



@Composable
fun ProjectOneApp(
    addStudentViewModel: AddStudentViewModel) {
    NavGraph(addStudentViewModel)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjectOneTheme {
    }
}