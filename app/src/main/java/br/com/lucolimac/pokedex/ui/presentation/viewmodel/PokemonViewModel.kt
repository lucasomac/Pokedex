package br.com.lucolimac.pokedex.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import br.com.lucolimac.pokedex.domain.usecase.PokedexUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class PokemonViewModel(
    private val pokedexUseCase: PokedexUseCase
) : ViewModel() {

    private val _pokemonList = MutableStateFlow(getPokemon(""))
    val pokemonList = _pokemonList.asStateFlow()

    private fun getPokemon(
        pokemonName: String
    ) {

    }
}