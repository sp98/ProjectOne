package com.santoshpillai.projectone.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.santoshpillai.projectone.ui.AppDestinations.ADDSTUDENTSCREEN
import com.santoshpillai.projectone.ui.AppDestinations.HOMESCREEN
import com.santoshpillai.projectone.ui.AppDestinations.NOTIFICATIONSCREEN
import com.santoshpillai.projectone.ui.AppDestinations.PERFORMANCESCREEN
import com.santoshpillai.projectone.ui.AppDestinations.STUDENTDETAILSCREEN
import com.santoshpillai.projectone.ui.home.HomeScreen
import com.santoshpillai.projectone.ui.notification.Notification
import com.santoshpillai.projectone.ui.performance.Performance
import com.santoshpillai.projectone.ui.student.AddStudentScreen
import com.santoshpillai.projectone.ui.student.AddStudentViewModel

object AppDestinations {
    const val HOMESCREEN = "homeScreen"
    const val NOTIFICATIONSCREEN = "notificationScreen"
    const val PERFORMANCESCREEN = "performanceScreen"
    const val STUDENTDETAILSCREEN = "studentDetailScreen"
    const val ADDSTUDENTSCREEN = "addStudentScreen"
}


class NavActions(navController: NavController){

    val navController = navController

    val toHomeScreen: () -> Unit = {
        navigateAndPopUpToHome(HOMESCREEN)
    }

    val toNotificationScreen: () -> Unit = {
        navigateAndPopUpToHome(NOTIFICATIONSCREEN)
    }

    val toPerformanceScreen: () -> Unit = {
        navigateAndPopUpToHome(PERFORMANCESCREEN)
    }

    val toStudentDetailScreen: () -> Unit = {
        navController.navigate(STUDENTDETAILSCREEN)
    }

    val toAddStudentScreen: () -> Unit = {
        navController.navigate(ADDSTUDENTSCREEN)
    }

    private fun navigateAndPopUpToHome(to: String){
        navController.navigate(to){
          popUpTo(navController.graph.findStartDestination().id){
              saveState = true
          }
        }
    }
}


@Composable
fun NavGraph(
    addStudentViewModel: AddStudentViewModel,
    startDestination:String = HOMESCREEN){

    // get all viewModels here
    val navController = rememberNavController()
    val navActions = remember(navController){ NavActions(navController)}
    
    NavHost(navController = navController, startDestination = startDestination){
        composable(HOMESCREEN){
            HomeScreen(navActions)
        }

        composable(PERFORMANCESCREEN){
            Performance(navActions)
        }

        composable(NOTIFICATIONSCREEN){
            Notification(navActions)
        }

        composable(ADDSTUDENTSCREEN){
            AddStudentScreen(
                navActions,
                addStudentViewModel
            )
        }
    }
}
