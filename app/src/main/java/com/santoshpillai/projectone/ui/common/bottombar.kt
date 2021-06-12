package com.santoshpillai.projectone.ui.common

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.QueryStats
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.santoshpillai.projectone.ui.AppDestinations.HOMESCREEN
import com.santoshpillai.projectone.ui.AppDestinations.NOTIFICATIONSCREEN
import com.santoshpillai.projectone.ui.AppDestinations.PERFORMANCESCREEN
import com.santoshpillai.projectone.ui.NavActions


sealed class BottomBarItem(val name:String, val route:String, val icon: ImageVector){
    object HomeScreen: BottomBarItem("Home", HOMESCREEN, Icons.Filled.Home)
    object NotificationScreen: BottomBarItem("Notification", NOTIFICATIONSCREEN, Icons.Filled.Notifications)
    object PerformanceScreen: BottomBarItem("Performance", PERFORMANCESCREEN, Icons.Filled.QueryStats)
}

val BottomBarItems = listOf(
    BottomBarItem.HomeScreen,
    BottomBarItem.PerformanceScreen,
    BottomBarItem.NotificationScreen,
)

@Composable
fun AppBottomBar(navActions: NavActions){
    BottomNavigation {
        val navBackStackEntry by navActions.navController.currentBackStackEntryAsState()
        val currentDestination  = navBackStackEntry?.destination
        BottomBarItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon , item.name)},
                onClick = {
                          navigate(navActions, item.route)
                },
                selected = currentDestination?.hierarchy?.any {it.route == item.route} == true
            )
        }
    }
}


fun navigate(navActions: NavActions, to:String){
    Log.i("testing", "to $to")
    when(to){
        HOMESCREEN -> navActions.toHomeScreen()
        NOTIFICATIONSCREEN -> navActions.toNotificationScreen()
        PERFORMANCESCREEN -> navActions.toPerformanceScreen()
    }
}