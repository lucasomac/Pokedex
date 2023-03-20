package br.com.lucolimac.pokedex.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.lucolimac.pokedex.domain.data.source.PokemonResumePagingSource
import br.com.lucolimac.pokedex.domain.entity.PokemonList
import br.com.lucolimac.pokedex.domain.usecase.PokemonListUseCase
import br.com.lucolimac.pokedex.domain.util.Constants.DEFAULT_SIZE_CONTENT_PAGE
import br.com.lucolimac.pokedex.domain.util.Constants.START_PAGE_INDEX
import br.com.lucolimac.pokedex.domain.util.Result
import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.presentation.state.ListPokemonState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class ListPokemonViewModel(
    private val pokemonListUseCase: PokemonListUseCase, val separator: Separator
) : ViewModel() {
//    fun state(
//        offset: Int = START_PAGE_INDEX, limit: Int = DEFAULT_SIZE_CONTENT_PAGE
//    ): StateFlow<ListPokemonState> {
//        return getListPokemon(offset, limit).stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5_000),
//            initialValue = ListPokemonState.Loading
//        )
//    }

    private val _pokemonList = MutableStateFlow<ListPokemonState>(ListPokemonState.Failure)
    val pokemonList = _pokemonList.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    suspend fun getListPokemon(
        offset: Int = START_PAGE_INDEX, limit: Int = DEFAULT_SIZE_CONTENT_PAGE
    ) {
        _loading.emit(true)
        pokemonListUseCase(offset, limit).collect {
            when (it) {
                is Result.Success -> _pokemonList.emit(ListPokemonState.Success(it.data))

                is Result.Error -> _pokemonList.emit(ListPokemonState.Error(it.data))

                is Result.Failure -> _pokemonList.emit(ListPokemonState.Failure)
            }
        }
        _loading.emit(false)
    }


    fun getPokemonResume(
        data: ListPokemonState.Success, offset: Int, limit: Int
    ): Flow<PagingData<PokemonList.PokemonResume>> {
        return Pager(config = PagingConfig(
            pageSize = limit
        ), pagingSourceFactory = { PokemonResumePagingSource(data, offset, limit) }).flow
    }
}