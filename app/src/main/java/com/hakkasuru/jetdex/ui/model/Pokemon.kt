package com.hakkasuru.jetdex.ui.model

data class Pokemon(
    val identifier: Int,
    val name: String,
    val pokeColor: PokeColor,
    val spriteURL: String,
    val types: List<String> = emptyList()
)

fun mockPokemonList(): List<Pokemon> {
    return listOf(
        Pokemon(
            identifier = 1,
            name = "bulbasaur",
            pokeColor = PokeColor.GREEN,
            spriteURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            types = listOf("grass", "poison")
        ),
        Pokemon(
            identifier = 4,
            name = "charmander",
            pokeColor = PokeColor.RED,
            spriteURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
            types = listOf("fire")
        ),
        Pokemon(
            identifier = 7,
            name = "squirtle",
            pokeColor = PokeColor.BLUE,
            spriteURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
            types = listOf("water")
        ),
        Pokemon(
            identifier = 25,
            name = "pikachu",
            pokeColor = PokeColor.YELLOW,
            spriteURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            types = listOf("electric")
        ),
        Pokemon(
            identifier = 25,
            name = "pikachu",
            pokeColor = PokeColor.YELLOW,
            spriteURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            types = listOf("electric")
        )
    )
}
