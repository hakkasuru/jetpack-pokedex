package com.hakkasuru.jetdex.api.model

import com.squareup.moshi.Json

data class Move(
    @field:Json(name = "name")
    val name: String = "",

    @field:Json(name = "pp")
    val powerPoint: Int? = null,

    @field:Json(name = "power")
    val power: Int? = null,

    @field:Json(name = "accuracy")
    val accuracy: Int? = null,

    @field:Json(name = "type")
    val type: Type? = null
) {
    data class Type(
        @field:Json(name = "name")
        val name: String = ""
    )
}
