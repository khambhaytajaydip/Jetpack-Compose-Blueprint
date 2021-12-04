package com.compose.blueprint.ui.home

import com.compose.blueprint.base.AppState
import com.compose.blueprint.base.BaseViewModel
import com.compose.blueprint.data.DataManager
import com.compose.blueprint.data.model.ResponseTeam
import com.compose.blueprint.data.network.NetworkResult
import com.compose.blueprint.utils.AppCoroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Jai khambhayta
 */

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val dataManager: DataManager) :
    BaseViewModel(dataManager) {
    private var isRunning = false
    init {
        if(!isRunning){
            isRunning = true
            state.value = AppState.Loading(true)
            AppCoroutines.main {
                fetchTeam()
            }
        }

    }

    private suspend fun fetchTeam() {
         withContext(Dispatchers.IO) {
            val data = dataManager.fetchTeamData()
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    state.value = AppState.Loading(false)
                    state.value =
                        AppState.Success(((data as NetworkResult.Success<*>).data as ResponseTeam).data)
                }
                // error in api calling
                is NetworkResult.Error -> {
                    state.value = AppState.Loading(false)
                    state.value =
                        AppState.Error(((data as NetworkResult.Error).error).message.toString())
                    Pair(
                        1,
                        ((data).error).message.toString()
                    )
                }
            }
        }
    }
}