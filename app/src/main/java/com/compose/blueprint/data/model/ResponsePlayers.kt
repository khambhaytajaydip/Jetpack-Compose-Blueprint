package com.compose.blueprint.data.model
/**
 * Jai Khambhayta
 */
data class ResponsePlayers (
    val data: MutableList<Player>
)
data class Player(
    var country_id: Int? = 0,
    var dateofbirth: String? = "",
    var firstname: String? = "",
    var fullname: String? = "",
    var gender: String? = "",
    var id: Int = 0,
    var image_path: String? = "",
    var lastname: String? = "",
    var position: Position,
    var resource: String? = "",
    var updated_at: String? = ""
)

data class Position(
    var id: Int? = 0,
    var name: String? = "",
    var resource: String? = ""
)