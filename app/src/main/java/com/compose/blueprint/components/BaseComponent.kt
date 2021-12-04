package com.compose.blueprint.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.compose.blueprint.ui.theme.ComposeBlueprintTheme
/**
 * Jai Khambhayta
 */
@ExperimentalMaterialApi
@Composable
fun BaseComponent(content: @Composable () -> Unit) {
    ComposeBlueprintTheme {
        content()
    }
}