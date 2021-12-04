package com.compose.blueprint

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.blueprint.components.BaseComponent
import com.compose.blueprint.ui.navigation.NavigationScreen
import com.compose.blueprint.ui.splash.SplashScreen
import com.compose.blueprint.utils.ScreensNavigation
/**
 * Jai Khambhayta
 */

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreensNavigation.SplashScreen.routes
    ) {
        // splash screen
        composable(ScreensNavigation.SplashScreen.routes) {
            BaseComponent {
                SplashScreen(navController = navController)
            }
        }
        //  Navigation  screen contain the bottom navigation/drawer
        composable(ScreensNavigation.NavigationScreen.routes) {
            BaseComponent {
                NavigationScreen()

            }
        }

    }
}
