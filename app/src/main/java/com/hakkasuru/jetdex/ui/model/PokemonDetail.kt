package com.hakkasuru.jetdex.ui.model

data class PokemonDetail(
    val identifier: Int,
    val name: String,
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
        val level: Int = 0
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
        pokeColor = PokeColor.YELLOW,
        spriteURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
        types = listOf("electric")
    )
}
