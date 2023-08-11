package com.hakkasuru.jetdex.ui.model

data class PokemonDetail(
    val identifier: Int,
    val name: String,
    val pokeColor: PokeColor,
    val spriteURL: String,
    val types: List<String> = emptyList()
)

fun mockPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        identifier = 25,
        name = "pikachu",
        pokeColor = PokeColor.YELLOW,
        spriteURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
        types = listOf("electric")
    )
}
