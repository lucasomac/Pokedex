package br.com.lucolimac.pokedex.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucolimac.pokedex.domain.usecase.PokemonUseCase
import br.com.lucolimac.pokedex.domain.util.Result
import br.com.lucolimac.pokedex.ui.presentation.state.PokemonState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

internal class PokemonViewModel(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {
    private val _pokemonState: MutableStateFlow<PokemonState> =
        MutableStateFlow(PokemonState.Failure)
    val pokemonState: StateFlow<PokemonState> = _pokemonState.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun getPokemonByName(pokemonName: String) {
        viewModelScope.launch {
            _loading.emit(true)
            pokemonUseCase(pokemonName).collectLatest {
                when (it) {
                    is Result.Success -> {
                        _pokemonState.emit(PokemonState.Success(it.data))
                    }
                    is Result.Error -> {
                        _pokemonState.emit(PokemonState.Error(it.data))
                    }
                    else -> _pokemonState.emit(PokemonState.Failure)
                }
            }
            _loading.emit(false)
        }
    }
}