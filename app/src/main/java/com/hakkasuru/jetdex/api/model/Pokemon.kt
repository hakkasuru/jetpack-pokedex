package com.hakkasuru.jetdex.api.model

import com.squareup.moshi.Json

data class Pokemon(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "sprites")
    val sprites: Sprites? = null,

    @field:Json(name = "types")
    val types: List<TypeItem> = emptyList()
) {
    data class Sprites(
        @field:Json(name = "front_default")
        val frontDefault: String? = null,

        @field:Json(name = "other")
        val other: Other? = null
    ) {
        data class Other(
            @field:Json(name = "official-artwork")
            val officialArtwork: OfficialArtwork? = null
        ) {
            data class OfficialArtwork(
                @field:Json(name = "front_default")
                val frontDefault: String? = null
            )
        }
    }

    data class TypeItem(
        @field:Json(name = "type")
        val type: Type
    ) {
        data class Type(
            @field:Json(name = "name")
            val name: String
        )
    }
}
