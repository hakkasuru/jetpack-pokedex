package com.hakkasuru.jetdex.api.model

import com.squareup.moshi.Json

data class Species(
    @field:Json(name = "flavor_text_entries")
    val flavouredTextEntries: List<Flavour> = emptyList()
) {
    data class Flavour(
        @field:Json(name = "flavor_text")
        val text: String = "",

        @field:Json(name = "language")
        val language: Language? = null,

        @field:Json(name = "version")
        val version: Version? = null
    ) {
        data class Language(
            @field:Json(name = "name")
            val name: String = ""
        )
        data class Version(
            @field:Json(name = "name")
            val name: String = ""
        )
    }
}
