package com.compose.blueprint.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose.blueprint.components.BaseComponent
import com.compose.blueprint.ui.home.HomeScreen
import com.compose.blueprint.ui.player.PlayerScreen
import com.compose.blueprint.ui.profile.ProfileScreen
import com.compose.blueprint.utils.ScreensNavigation

/**
 * @author Jai khambhayta
 */


@ExperimentalMaterialApi
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            // visible bottom bar only on home and profile screen
            if ((currentRoute(navController = navController) == ScreensNavigation.HomeScreen.routes) or (currentRoute(
                    navController = navController
                ) == ScreensNavigation.ProfileScreen.routes)
            ) {
                BottomBar(navController)
            }

        }
    ) {
        BottomBarMain(navController)
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
fun BottomBarMain(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreensNavigation.HomeScreen.routes) {
        // redirect to Home screen
        composable(ScreensNavigation.HomeScreen.routes) {
            BaseComponent {
                HomeScreen(navController)
            }
        }
        // redirect to profile screen
        composable(ScreensNavigation.ProfileScreen.routes) {
            BaseComponent {
                ProfileScreen()

            }
        }
        // navigate to the players screen
        composable(ScreensNavigation.PlayersScreen.routes.plus("/{team}/{id}"), arguments = listOf(
            navArgument("team"){},navArgument("id"){})) {
            // get the argument here
            BaseComponent {
                PlayerScreen(navController,it.arguments!!.getString("team")!!)
            }
        }
    }
}


@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        ScreensNavigation.HomeScreen,
        ScreensNavigation.ProfileScreen
    )
    BottomNavigation(elevation = 5.dp) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title
                    )
                },
                label = {
                    Text(
                        text = it.title
                    )
                },
                selected = currentRoute == it.routes,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
                onClick = {
                    navController.navigate(it.routes) {
                        launchSingleTop = true
                        navController.graph.startDestinationRoute.let { route ->
                            popUpTo(route!!) {
                                saveState = true
                            }
                        }
                        restoreState = true
                    }
                }
            )
        }
    }
}