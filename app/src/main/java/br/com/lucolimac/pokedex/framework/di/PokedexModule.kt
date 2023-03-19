package br.com.lucolimac.pokedex.framework.di

import br.com.lucolimac.pokedex.data.PokemonApi.provideOkHttpClient
import br.com.lucolimac.pokedex.data.PokemonApi.provideRetrofit
import br.com.lucolimac.pokedex.data.repository.PokedexRepositoryImpl
import br.com.lucolimac.pokedex.domain.usecase.PokemonListUseCaseImpl
import br.com.lucolimac.pokedex.framework.data.source.PokedexDataSourceImpl
import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.presentation.viewmodel.ListPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal object PokedexModule {
    val pokedexModule = module {
        factory { Separator(4) }
        factory { provideOkHttpClient() }
        factory { provideRetrofit(get()) }
        factory { PokedexDataSourceImpl(get()) }
        factory { PokedexRepositoryImpl(get()) }
        factory { PokemonListUseCaseImpl(get()) }
        viewModel { ListPokemonViewModel(get(), get()) }
    }
}