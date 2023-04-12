package br.com.lucolimac.pokedex.ui.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.lucolimac.pokedex.domain.data.source.PokemonResumePagingSource
import br.com.lucolimac.pokedex.domain.entity.Pokedex
import br.com.lucolimac.pokedex.domain.usecase.PokedexUseCase
import br.com.lucolimac.pokedex.domain.util.Constants.DEFAULT_SIZE_CONTENT_PAGE
import br.com.lucolimac.pokedex.domain.util.Constants.START_PAGE_INDEX
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class PokedexViewModel(
    private val pokedexUseCase: PokedexUseCase
) : ViewModel() {

    private val _pokemonList = MutableStateFlow(getListPokemon())
    val pokemonList = _pokemonList.asStateFlow()

    private fun getListPokemon(
        offset: Int = START_PAGE_INDEX, limit: Int = DEFAULT_SIZE_CONTENT_PAGE
    ): Flow<PagingData<Pokedex.PokemonResume>> {
        return Pager(config = PagingConfig(
            pageSize = limit
        ), pagingSourceFactory = {
            PokemonResumePagingSource(
                pokedexUseCase, offset, limit
            )
        }).flow.cachedIn(viewModelScope)
    }
}