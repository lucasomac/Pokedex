package br.com.lucolimac.pokedex.domain.util

import br.com.lucolimac.pokedex.domain.entity.PokemonList
import br.com.lucolimac.pokedex.domain.repository.PokedexRepository
import br.com.lucolimac.pokedex.domain.usecase.PokemonListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class PokemonListUseCaseImpl constructor(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val exampleRepository: PokedexRepository
) : PokemonListUseCase {

    override operator fun invoke(offset: Int, limit: Int): Flow<Result<PokemonList, String>> {
        return flow {
            emit(exampleRepository.getPokemonList(offset, limit))
        }.flowOn(coroutineDispatcher)
    }
}