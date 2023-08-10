package com.hakkasuru.jetdex.api.model

import com.squareup.moshi.Json

data class PokemonList(
    @field:Json(name = "results")
    val results: List<Pokemon>
) {
    data class Pokemon(
        @field:Json(name = "name")
        val name: String = ""
    )
}
