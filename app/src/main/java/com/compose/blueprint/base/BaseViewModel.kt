package com.compose.blueprint.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
/**
 * Jai Khambhayta
 */
abstract class BaseViewModel(obj: Any) : ViewModel() {
    val state: MutableState<AppState> = mutableStateOf(AppState.Loading(false))
}

sealed class AppState {
    data class Loading(val boolean: Boolean = false) : AppState()
    data class Success(val any: Any) : AppState()
    data class Error(val error: String? = null) : AppState()
}