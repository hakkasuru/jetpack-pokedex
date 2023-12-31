package com.hakkasuru.jetdex.ui.model

data class PokemonDetail(
    val identifier: Int,
    val name: String,
    val description: String,
    val pokeColor: PokeColor,
    val spriteURL: String,
    val types: List<String> = emptyList(),
    val stats: List<Stat> = emptyList(),
    val movesByLevel: List<Move> = emptyList(),
    val abilities: List<Ability> = emptyList()
) {
    data class Stat(
        val name: String = "",
        val base: Int = 0
    )

    data class Move(
        val name: String = "",
        val level: Int = 0,
        val pp: Int? = null,
        val power: Int? = null,
        val accuracy: Int? = null,
        val type: String = "",
        val pokeColor: PokeColor = PokeColor.BLACK
    )

    data class Ability(
        val name: String = "",
        val hidden: Boolean = false
    )
}

fun mockPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        identifier = 25,
        name = "pikachu",
        description = "When several of these POKéMON gather, their electricity could build and cause lightning storms.",
        pokeColor = PokeColor.YELLOW,
        spriteURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
        types = listOf("electric")
    )
}
