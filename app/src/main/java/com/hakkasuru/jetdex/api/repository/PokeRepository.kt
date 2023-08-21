package com.hakkasuru.jetdex.api.repository

import com.hakkasuru.jetdex.api.service.PokeService
import com.hakkasuru.jetdex.ui.model.PokeColor
import com.hakkasuru.jetdex.ui.model.Pokemon
import com.hakkasuru.jetdex.ui.model.PokemonDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

    fun getPokemonDetail(id: Int): Flow<PokemonDetail> = flow {
        val pokemonResponse = pokeService.getPokemonByID(id)
        val pokeTypes = pokemonResponse.types.map { typeItem ->
            typeItem.type.name
        }
        val pokeStats = pokemonResponse.stats.map {
            PokemonDetail.Stat(
                name = it.statDetail?.name ?: "",
                base = it.baseStat
            )
        }
        val pokeMovesByLevelUp = pokemonResponse.moves.mapNotNull { moveItem ->
            val versionDetails =
                moveItem.versionGroupDetails.find {
                    (it.version?.name == "red-blue" || it.version?.name == "firered-leafgreen") &&
                            it.method?.name == "level-up"
                }
            versionDetails?.let { versionDetail ->
                val moveResponse = moveItem.move?.name?.let { name -> pokeService.getMoveByName(name) }
                PokemonDetail.Move(
                    name = moveItem.move?.name ?: "",
                    level = versionDetail.level,
                    pp = moveResponse?.powerPoint,
                    power  = moveResponse?.power,
                    accuracy = moveResponse?.accuracy,
                    type = moveResponse?.type?.name ?: "",
                    pokeColor = typeToColor(moveResponse?.type?.name ?: "")
                )
            }
        }.sortedBy { it.level }
        val pokeAbilities = pokemonResponse.abilities.map {
            PokemonDetail.Ability(
                name = it.ability?.name ?: "",
                hidden = it.hidden
            )
        }
        val pokemon = PokemonDetail(
            identifier = pokemonResponse.id,
            name = pokemonResponse.name,
            pokeColor = typeToColor(pokeTypes.getOrNull(0) ?: ""),
            spriteURL = pokemonResponse.sprites?.other?.officialArtwork?.frontDefault ?: "",
            types = pokeTypes,
            stats = pokeStats,
            movesByLevel = pokeMovesByLevelUp,
            abilities = pokeAbilities
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