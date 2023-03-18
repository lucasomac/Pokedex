package br.com.lucolimac.pokedex.data.service

import br.com.lucolimac.pokedex.data.model.response.PokemonResponse
import br.com.lucolimac.pokedex.data.model.response.PokemonListResponse
import br.com.lucolimac.pokedex.data.util.Constants.POKE_API_BASE_PATH
import br.com.lucolimac.pokedex.data.util.Constants.POKE_API_POKEMON_PATH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokedexService {
    @GET(POKE_API_BASE_PATH)
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response<PokemonListResponse>

    @GET(POKE_API_POKEMON_PATH)
    suspend fun getPokemonByName(@Path("name") name: String): Response<PokemonResponse>
}