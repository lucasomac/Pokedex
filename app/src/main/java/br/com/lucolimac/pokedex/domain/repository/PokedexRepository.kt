package br.com.lucolimac.pokedex.domain.repository

import br.com.lucolimac.pokedex.domain.entity.Pokemon
import br.com.lucolimac.pokedex.domain.entity.PokemonList
import br.com.lucolimac.pokedex.domain.util.Result

internal interface PokedexRepository {
    suspend fun getPokemonList(offset: Int, limit: Int): Result<PokemonList, String>

    suspend fun getPokemonByName(name: String): Result<Pokemon, String>
}