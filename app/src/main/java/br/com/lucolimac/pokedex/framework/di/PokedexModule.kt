package br.com.lucolimac.pokedex.framework.di

import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.presentation.viewmodel.ListPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal object PokedexModule {
    val pokedexModule = module {
        factoryOf(::Separator)
        viewModelOf(::ListPokemonViewModel)
    }
}