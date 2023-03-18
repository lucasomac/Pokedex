package br.com.lucolimac.pokedex.data.source

import br.com.lucolimac.pokedex.data.model.response.PokemonListResponse
import br.com.lucolimac.pokedex.data.model.response.PokemonResponse
import retrofit2.Response

internal interface PokedexDataSource {
    suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonListResponse>
    suspend fun getPokemonByName(name: String): Response<PokemonResponse>
}