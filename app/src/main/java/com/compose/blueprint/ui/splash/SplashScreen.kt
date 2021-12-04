package com.compose.blueprint.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.compose.blueprint.R
import com.compose.blueprint.utils.ScreensNavigation
import kotlinx.coroutines.delay

/**
 * Jai Khambhayta
 */

@ExperimentalAnimationApi
@Composable
fun SplashScreen(navController: NavController) {
    Scaffold(content = {
        val scale = remember {
            androidx.compose.animation.core.Animatable(0f)
        }
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.3f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )
            //delay for 3 seconds
            delay(3000L)
            // to remove from the stack
            navController.popBackStack()
            // navigate to other screen
            navController.navigate(ScreensNavigation.NavigationScreen.routes)
        }
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colors.primary)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.scale(scale.value)
            )
        }
    })
}