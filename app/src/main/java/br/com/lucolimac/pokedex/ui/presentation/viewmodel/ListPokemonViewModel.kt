package br.com.lucolimac.pokedex.ui.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucolimac.pokedex.domain.usecase.PokemonListUseCase
import br.com.lucolimac.pokedex.domain.util.Result
import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.presentation.state.ListPokemonState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class ListPokemonViewModel(
    private val pokemonListUseCase: PokemonListUseCase, val separator: Separator
) : ViewModel() {
    fun exampleUiStateFlow(offset: Int, limit: Int): StateFlow<ListPokemonState> =
        getListPokemon(offset, limit).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ListPokemonState.Loading
        )

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getListPokemon(offset: Int, limit: Int): Flow<ListPokemonState> {
        return pokemonListUseCase(offset, limit).map {
            when (it) {
                is Result.Success -> {
                    ListPokemonState.Success(it.data)
                }

                else -> {
                    ListPokemonState.Error
                }
            }
        }.catch {
            emit(ListPokemonState.Error)
        }
    }
}