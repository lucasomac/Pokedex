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
    /**
     * Invokes the use case to fetch a list of Pokémon.
     *
     * @param offset The starting point of the Pokémon list.
     * @param limit The maximum number of Pokémon to retrieve.
     * @return A [Flow] emitting a [Result] which contains either a [Pokedex] object on success
     *         or an error message [String] on failure.
     */
    override operator fun invoke(offset: Int, limit: Int): Flow<Result<Pokedex, String>> {
        // We use Flow to handle asynchronous data streams, allowing us to emit results
        // progressively and handle them in a reactive manner.
        // This is particularly useful for operations that may take time, such as network requests,
        // and allows us to manage the data flow efficiently without blocking the main thread.
        return flow {
            emit(pokedexRepository.getPokemonList(offset, limit))
        }.flowOn(coroutineDispatcher)
    }
}