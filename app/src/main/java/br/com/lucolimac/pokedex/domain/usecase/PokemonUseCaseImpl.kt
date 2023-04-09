package br.com.lucolimac.pokedex.domain.usecase

import br.com.lucolimac.pokedex.domain.entity.Pokemon
import br.com.lucolimac.pokedex.domain.repository.PokedexRepository
import br.com.lucolimac.pokedex.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class PokemonUseCaseImpl(
    private val pokedexRepository: PokedexRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonUseCase {
    override fun invoke(pokemonName: String): Flow<Result<Pokemon, String>> {
        return flow {
            emit(pokedexRepository.getPokemonByName(pokemonName))
        }.flowOn(coroutineDispatcher)
    }
}