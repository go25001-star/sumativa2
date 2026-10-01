package com.example.sumativa2mdgo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sumativa2mdgo.screens.CalculadoraScreen
import com.example.sumativa2mdgo.screens.ResumenScreen

@Composable
fun NavManager() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "calculadora") {

        composable("calculadora") {
            CalculadoraScreen(navController)
        }
        composable(
            route = "resumen/{platillo}/{propina}/{total}",
            arguments = listOf(
                navArgument("platillo") { type = NavType.StringType },
                navArgument("propina") { type = NavType.FloatType },
                navArgument("total") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val platillo = backStackEntry.arguments?.getString("platillo") ?: ""
            val propina = backStackEntry.arguments?.getFloat("propina") ?: 0f
            val total = backStackEntry.arguments?.getFloat("total") ?: 0f
            ResumenScreen(navController, platillo, propina, total)
        }
    }
}
