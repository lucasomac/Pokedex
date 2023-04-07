package br.com.lucolimac.pokedex.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.lucolimac.pokedex.domain.data.source.PokemonResumePagingSource
import br.com.lucolimac.pokedex.domain.entity.PokemonList
import br.com.lucolimac.pokedex.domain.usecase.PokemonListUseCase
import br.com.lucolimac.pokedex.domain.util.Constants.DEFAULT_SIZE_CONTENT_PAGE
import br.com.lucolimac.pokedex.domain.util.Constants.START_PAGE_INDEX
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class ListPokemonViewModel(
    private val pokemonListUseCase: PokemonListUseCase
) : ViewModel() {

    private val _pokemonList = MutableStateFlow(getListPokemon())
    val pokemonList = _pokemonList.asStateFlow()

    private fun getListPokemon(
        offset: Int = START_PAGE_INDEX, limit: Int = DEFAULT_SIZE_CONTENT_PAGE
    ): Flow<PagingData<PokemonList.PokemonResume>> {
        return Pager(config = PagingConfig(
            pageSize = limit
        ), pagingSourceFactory = {
            PokemonResumePagingSource(
                pokemonListUseCase, offset, limit
            )
        }).flow.cachedIn(viewModelScope)
    }
}