package com.hakkasuru.jetdex.ui.model

data class PokemonDetail(
    val identifier: Int,
    val name: String,
    val pokeColor: PokeColor,
    val spriteURL: String,
    val types: List<String> = emptyList()
)
