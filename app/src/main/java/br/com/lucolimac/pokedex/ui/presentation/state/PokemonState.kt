package br.com.lucolimac.pokedex.ui.presentation.state

import br.com.lucolimac.pokedex.domain.entity.Pokemon

internal sealed class PokemonState {
    internal data class Success(
        val pokemon: Pokemon
    ) : PokemonState()

    internal class Error(val message: String) : PokemonState()
    internal object Failure : PokemonState()
}