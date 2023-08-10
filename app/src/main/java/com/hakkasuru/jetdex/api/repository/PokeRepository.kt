package com.hakkasuru.jetdex.api.repository

import android.util.Log
import com.hakkasuru.jetdex.api.service.PokeService
import com.hakkasuru.jetdex.ui.model.PokeColor
import com.hakkasuru.jetdex.ui.model.Pokemon
import com.hakkasuru.jetdex.ui.model.PokemonDetail
import com.hakkasuru.jetdex.ui.model.mockPokemonList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokeRepository(private val pokeService: PokeService) {

    fun getPokemons(): Flow<List<Pokemon>> = flow {
        val pokemonsResponse = pokeService.getPokemons(limit = 151)
        val mPokemons = pokemonsResponse.results.map {
            val pokemonResponse = pokeService.getPokemonByName(it.name)
            val pokeTypes = pokemonResponse.types.map { typeItem ->
                typeItem.type.name
            }
            Pokemon(
                identifier = pokemonResponse.id,
                name = pokemonResponse.name,
                pokeColor = typeToColor(pokeTypes.getOrNull(0) ?: ""),
                spriteURL = pokemonResponse.sprites?.other?.officialArtwork?.frontDefault ?: "",
                types = pokeTypes
            )
        }
        emit(mPokemons)
    }

    fun getPokemon(id: Int): Flow<PokemonDetail> = flow {
        val pokemonResponse = pokeService.getPokemonByID(id)
        val pokeTypes = pokemonResponse.types.map { typeItem ->
            typeItem.type.name
        }
        val pokemon = PokemonDetail(
            identifier = pokemonResponse.id,
            name = pokemonResponse.name,
            pokeColor = typeToColor(pokeTypes.getOrNull(0) ?: ""),
            spriteURL = pokemonResponse.sprites?.other?.officialArtwork?.frontDefault ?: "",
            types = pokeTypes
        )
        emit(pokemon)
    }

    private fun typeToColor(type: String): PokeColor {
        return when (type) {
            "fire" -> PokeColor.RED
            "grass", "bug" -> PokeColor.GREEN
            "fighting" -> PokeColor.LIGHT_BROWN
            "ground", "rock" -> PokeColor.BROWN
            "electric" -> PokeColor.YELLOW
            "water", "ice" -> PokeColor.BLUE
            "flying" -> PokeColor.LIGHT_BLUE
            "poison", "dragon" -> PokeColor.PURPLE
            "psychic", "ghost" -> PokeColor.LIGHT_PURPLE
            "dark" -> PokeColor.BLACK
            "normal", "steel" -> PokeColor.OLIVE
            "fairy" -> PokeColor.PINK
            else -> PokeColor.BLACK
        }
    }
}