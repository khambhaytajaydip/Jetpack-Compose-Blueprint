package com.compose.blueprint.data

import com.compose.blueprint.base.BaseRepository
import com.compose.blueprint.constants.AppConstants
import com.compose.blueprint.data.network.NetworkCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(private val networkCall: NetworkCall):BaseRepository() {
    /**
     * API call
     */
    suspend fun fetchTeamData(): Any {
        val data =
            safeApiCall({ networkCall.getTeamData(AppConstants.API_TOKEN_VALUE) }, "No response")
        return data!!
    }

    suspend fun fetchPlayerData(): Any {
        val data =
            safeApiCall({ networkCall.getPlayerData(AppConstants.API_TOKEN_VALUE) }, "No response")
        return data!!
    }
}