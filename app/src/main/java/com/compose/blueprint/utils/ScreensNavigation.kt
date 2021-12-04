package com.compose.blueprint.utils

import androidx.annotation.DrawableRes
import com.compose.blueprint.R
/**
 * Jai Khambhayta
 */
sealed class ScreensNavigation(val routes:String,val title:String = "",@DrawableRes val icon: Int = R.drawable.logo) {
    object SplashScreen : ScreensNavigation("SplashScreen")
    object NavigationScreen : ScreensNavigation("NavigationScreen")
    object HomeScreen : ScreensNavigation("HomeScreen", title = "Home", icon = android.R.drawable.ic_menu_help)
    object ProfileScreen: ScreensNavigation("ProfileScreen", title = "Profile",icon = android.R.drawable.ic_menu_search)
    object PlayersScreen: ScreensNavigation("PlayerScreen",title = "player",icon = android.R.drawable.ic_media_play)

}