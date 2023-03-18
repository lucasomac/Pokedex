package br.com.lucolimac.pokedex.ui.presentation.state

import br.com.lucolimac.pokedex.domain.entity.PokemonList

internal sealed class ListPokemonState {

    internal object Loading : ListPokemonState()

    internal data class Success constructor(
        val pokemonList: PokemonList
    ) : ListPokemonState()

    internal object Error : ListPokemonState()
}