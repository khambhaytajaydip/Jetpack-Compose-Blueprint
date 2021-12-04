package com.compose.blueprint.data.network

import com.compose.blueprint.constants.AppConstants
import com.compose.blueprint.data.model.ResponsePlayers
import com.compose.blueprint.data.model.ResponseTeam
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton
/**
 * Jai Khambhayta
 */
@Singleton
interface NetworkCall {
    @GET(AppConstants.API_TEAMS)
    suspend fun getTeamData(@Query(AppConstants.API_TOKEN_KEY) value: String): Response<ResponseTeam>

    @GET(AppConstants.API_PLAYERS)
    suspend fun getPlayerData(@Query(AppConstants.API_TOKEN_KEY) value: String): Response<ResponsePlayers>
}