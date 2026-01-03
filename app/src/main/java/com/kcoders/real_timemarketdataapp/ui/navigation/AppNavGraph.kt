package com.kcoders.real_timemarketdataapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kcoders.real_timemarketdataapp.ui.screens.ConnectionStatusScreen
import com.kcoders.real_timemarketdataapp.ui.screens.DetailScreen
import com.kcoders.real_timemarketdataapp.ui.screens.MarketWatchScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "market") {

        composable("market") {
            MarketWatchScreen(navController)
        }
        composable("detail") {
            DetailScreen()
        }
        composable("status") {
            ConnectionStatusScreen()
        }
    }
}
