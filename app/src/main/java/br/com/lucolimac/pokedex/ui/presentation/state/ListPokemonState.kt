package br.com.lucolimac.pokedex.ui.presentation.state

import br.com.lucolimac.pokedex.domain.entity.Pokedex

internal sealed class ListPokemonState {
    internal data class Success constructor(
        val pokedex: Pokedex
    ) : ListPokemonState()

    internal class Error(val message: String) : ListPokemonState()
    internal object Failure : ListPokemonState()
}