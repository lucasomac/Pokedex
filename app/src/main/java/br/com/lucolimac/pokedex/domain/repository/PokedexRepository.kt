package br.com.lucolimac.pokedex.domain.repository

import br.com.lucolimac.pokedex.domain.entity.Pokemon
import br.com.lucolimac.pokedex.domain.entity.Pokedex
import br.com.lucolimac.pokedex.domain.util.Result

internal interface PokedexRepository {
    suspend fun getPokemonList(offset: Int, limit: Int): Result<Pokedex, String>

    suspend fun getPokemonByName(name: String): Result<Pokemon, String>
}