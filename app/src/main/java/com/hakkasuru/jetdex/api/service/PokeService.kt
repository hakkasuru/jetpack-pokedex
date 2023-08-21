package com.hakkasuru.jetdex.api.service

import com.hakkasuru.jetdex.api.model.Move
import com.hakkasuru.jetdex.api.model.Pokemon
import com.hakkasuru.jetdex.api.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {
    @GET("/api/v2/pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int = 151
    ): PokemonList

    @GET("/api/v2/pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String
    ): Pokemon

    @GET("/api/v2/pokemon/{id}")
    suspend fun getPokemonByID(
        @Path("id") id: Int
    ): Pokemon

    @GET("/api/v2/move/{name}")
    suspend fun getMoveByName(
        @Path("name") name: String
    ): Move
}