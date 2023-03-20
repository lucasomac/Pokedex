package br.com.lucolimac.pokedex.ui.presentation.state

import br.com.lucolimac.pokedex.domain.entity.PokemonList

internal sealed class ListPokemonState {
    internal data class Success constructor(
        val pokemonList: PokemonList
    ) : ListPokemonState()

    internal class Error(val message: String) : ListPokemonState()
    internal object Failure : ListPokemonState()
}