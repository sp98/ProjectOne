package com.santoshpillai.projectone.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.santoshpillai.projectone.ui.AppDestinations.HOMESCREEN
import com.santoshpillai.projectone.ui.AppDestinations.NOTIFICATIONSCREEN
import com.santoshpillai.projectone.ui.AppDestinations.PERFORMANCESCREEN
import com.santoshpillai.projectone.ui.AppDestinations.STUDENTDETAILSCREEN
import com.santoshpillai.projectone.ui.home.HomeScreen
import com.santoshpillai.projectone.ui.notification.Notification
import com.santoshpillai.projectone.ui.performance.Performance

object AppDestinations {
    const val HOMESCREEN = "homeScreen"
    const val NOTIFICATIONSCREEN = "notificationScreen"
    const val PERFORMANCESCREEN = "performanceScreen"
    const val STUDENTDETAILSCREEN = "studentDetailScreen"
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

    private fun navigateAndPopUpToHome(to: String){
        navController.navigate(to){
          popUpTo(navController.graph.findStartDestination().id){
              saveState = true
          }
        }
    }
}


@Composable
fun NavGraph(startDestination:String = HOMESCREEN){
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
    }
}