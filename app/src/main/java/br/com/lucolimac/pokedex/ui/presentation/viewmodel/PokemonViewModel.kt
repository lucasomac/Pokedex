package br.com.lucolimac.pokedex.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import br.com.lucolimac.pokedex.domain.usecase.PokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class PokemonViewModel(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    private val _pokemonList = MutableStateFlow(getPokemonByName(""))
    val pokemonList = _pokemonList.asStateFlow()

    private fun getPokemonByName(
        pokemonName: String
    ) {
        pokemonUseCase(pokemonName)
    }
}