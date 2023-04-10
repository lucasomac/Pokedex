package br.com.lucolimac.pokedex.domain.usecase

import br.com.lucolimac.pokedex.domain.entity.Pokedex
import br.com.lucolimac.pokedex.domain.repository.PokedexRepository
import br.com.lucolimac.pokedex.domain.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class PokedexUseCaseImpl constructor(
    private val pokedexRepository: PokedexRepository,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokedexUseCase {
    override operator fun invoke(offset: Int, limit: Int): Flow<Result<Pokedex, String>> {
        return flow {
            emit(pokedexRepository.getPokemonList(offset, limit))
        }.flowOn(coroutineDispatcher)
    }
}