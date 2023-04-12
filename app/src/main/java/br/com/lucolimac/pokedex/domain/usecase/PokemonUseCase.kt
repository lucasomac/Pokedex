package br.com.lucolimac.pokedex.domain.usecase

import br.com.lucolimac.pokedex.domain.entity.Pokemon
import br.com.lucolimac.pokedex.domain.util.Result
import kotlinx.coroutines.flow.Flow

internal interface PokemonUseCase {
    operator fun invoke(pokemonName: String): Flow<Result<Pokemon, String>>
}