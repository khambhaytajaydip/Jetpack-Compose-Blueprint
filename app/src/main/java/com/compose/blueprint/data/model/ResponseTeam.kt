package com.compose.blueprint.data.model
/**
 * Jai Khambhayta
 */
data class ResponseTeam(
    val data: MutableList<Team>
)

data class Team(
    val code: String? = "",
    val country_id: Int? = 0,
    val id: Int? = 0,
    val image_path: String? = "",
    val name: String? = "",
    val national_team: Boolean? = false,
    val resource: String? = "",
    val updated_at: String? = ""
)