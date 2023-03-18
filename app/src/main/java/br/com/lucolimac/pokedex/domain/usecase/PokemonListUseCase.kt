package br.com.lucolimac.pokedex.domain.usecase

import br.com.lucolimac.pokedex.domain.entity.PokemonList
import br.com.lucolimac.pokedex.domain.util.Result
import kotlinx.coroutines.flow.Flow

internal interface PokemonListUseCase {
    operator fun invoke(offset: Int, limit: Int): Flow<Result<PokemonList, String>>
}