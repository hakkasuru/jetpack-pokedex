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
    val types: List<TypeItem> = emptyList(),

    @field:Json(name = "stats")
    val stats: List<Stat> = emptyList(),

    @field:Json(name = "moves")
    val moves: List<MoveItem> = emptyList(),

    @field:Json(name = "abilities")
    val abilities: List<AbilityItem> = emptyList()
) {

    data class AbilityItem(
        @field:Json(name = "ability")
        val ability: Ability? = null,

        @field:Json(name = "is_hidden")
        val hidden: Boolean = false
    ) {
        data class Ability(
            @field:Json(name = "name")
            val name: String = ""
        )
    }

    data class MoveItem(
        @field:Json(name = "move")
        val move: Move? = null,

        @field:Json(name = "version_group_details")
        val versionGroupDetails: List<VersionGroupDetail> = emptyList()
    ) {
        data class Move(
            @field:Json(name = "name")
            val name: String = ""
        )

        data class VersionGroupDetail(
            @field:Json(name = "level_learned_at")
            val level: Int = 0,

            @field:Json(name = "move_learn_method")
            val method: LearnMethod? = null,

            @field:Json(name = "version_group")
            val version: VersionGroup? = null
        ) {
            data class LearnMethod(
                @field:Json(name = "name")
                val name: String = ""
            )

            data class VersionGroup(
                @field:Json(name = "name")
                val name: String = ""
            )
        }
    }

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

    data class Stat(
        @field:Json(name = "base_stat")
        val baseStat: Int = 0,

        @field:Json(name = "effort")
        val effort: Int = 0,

        @field:Json(name = "stat")
        val statDetail: StatDetail? = null
    ) {
        data class StatDetail(
            @field:Json(name = "name")
            val name: String = ""
        )
    }
}
