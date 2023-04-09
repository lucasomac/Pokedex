package br.com.lucolimac.pokedex.domain.usecase

import br.com.lucolimac.pokedex.domain.entity.Pokedex
import br.com.lucolimac.pokedex.domain.util.Result
import kotlinx.coroutines.flow.Flow

internal interface PokedexUseCase {
    operator fun invoke(offset: Int, limit: Int): Flow<Result<Pokedex, String>>
}