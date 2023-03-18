package br.com.lucolimac.pokedex.data.repository

import br.com.lucolimac.pokedex.data.source.PokedexDataSource
import br.com.lucolimac.pokedex.domain.entity.Pokemon
import br.com.lucolimac.pokedex.domain.entity.PokemonList
import br.com.lucolimac.pokedex.domain.repository.PokedexRepository
import br.com.lucolimac.pokedex.domain.util.Result

internal class PokedexRepositoryImpl(private val pokedexDataSource: PokedexDataSource) :
    PokedexRepository {
    override suspend fun getPokemonList(offset: Int, limit: Int): Result<PokemonList, String> {
        return try {
            val response = pokedexDataSource.getPokemonList(offset, limit)
            if (response.isSuccessful) {
                val pokemonListResponse = response.body() ?: return Result.Failure()
                Result.Success(pokemonListResponse.toEntity())
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    override suspend fun getPokemonByName(name: String): Result<Pokemon, String> {
        return try {
            val response = pokedexDataSource.getPokemonByName(name)
            if (response.isSuccessful) {
                val pokemonResponse = response.body() ?: return Result.Failure()
                Result.Success(pokemonResponse.toEntity())
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }
}