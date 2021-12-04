package com.compose.blueprint.ui.player

import android.util.Log
import com.compose.blueprint.base.AppState
import com.compose.blueprint.base.BaseViewModel
import com.compose.blueprint.data.DataManager
import com.compose.blueprint.data.model.Player
import com.compose.blueprint.data.model.ResponsePlayers
import com.compose.blueprint.data.network.NetworkResult
import com.compose.blueprint.utils.AppCoroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext
import javax.inject.Inject
/**
 * Jai Khambhayta
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class PlayerScreenViewModel @Inject constructor(val dataManager: DataManager) :
    BaseViewModel(dataManager) {
    private var isRunning = false

    init {
        AppCoroutines.main {
            getPlayers()
        }
    }
    suspend fun getPlayers() {
        if (!isRunning) {
            isRunning = true
            withContext(Dispatchers.IO) {
                val data = dataManager.fetchPlayerData()
                when ((data as NetworkResult<Any>)) {
                    is NetworkResult.Success<Any> -> {
                        // player data get
                        state.value = AppState.Loading(false)
                        state.value =
                            AppState.Success(((data as NetworkResult.Success<*>).data as ResponsePlayers).data as List<Player>)
                        // get only selected team players

                    }
                    // error in api calling
                    is NetworkResult.Error -> {
                        Log.d("jai", "API error"+(data as NetworkResult.Error).error)
                        state.value = AppState.Loading(false)
                        state.value =
                            AppState.Error(((data as NetworkResult.Error).error).message.toString())
                    }
                }
            }
        }
    }


}