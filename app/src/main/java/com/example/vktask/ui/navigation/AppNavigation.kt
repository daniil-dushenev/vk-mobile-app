package com.example.vktask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.vktask.data.AppDatabase
import com.example.vktask.repository.AppRepository
import com.example.vktask.ui.screens.*
import com.example.vktask.ui.viewmodel.*
import androidx.compose.ui.platform.LocalContext


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val context = LocalContext.current
    val repo = AppRepository(AppDatabase.get(context).appDao())

    val listVM = AppListViewModel(repo)
    val detailsVM = AppDetailsViewModel(repo)

    NavHost(navController, startDestination = "list") {

        composable("list") {
            AppListScreen(
                vm = listVM,
                onOpenDetails = { id -> navController.navigate("details/$id") }
            )
        }

        composable(
            route = "details/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments!!.getInt("id")
            AppDetailsScreen(
                id = id,
                vm = detailsVM,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
