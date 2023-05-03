package br.com.lucolimac.pokedex.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucolimac.pokedex.domain.entity.Pokemon
import br.com.lucolimac.pokedex.domain.usecase.PokemonUseCase
import br.com.lucolimac.pokedex.domain.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class PokemonViewModel(
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _pokemon = MutableStateFlow(null as Pokemon?)
    val pokemon: StateFlow<Pokemon?> = _pokemon.asStateFlow()

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error.asStateFlow()

    fun getPokemonByName(pokemonName: String) {
        viewModelScope.launch {
            pokemonUseCase(pokemonName).onStart { _loading.emit(true) }
                .onCompletion { _loading.emit(false) }.flowOn(Dispatchers.IO).collect {
                    when (it) {
                        is Result.Success -> _pokemon.emit(it.data)
                        is Result.Error -> _error.emit(it.data)
                        else -> _error.emit(it.toString())
                    }
                }
        }
    }
}