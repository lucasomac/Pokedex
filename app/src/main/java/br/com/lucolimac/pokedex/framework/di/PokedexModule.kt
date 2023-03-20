package br.com.lucolimac.pokedex.framework.di

import br.com.lucolimac.pokedex.data.PokemonApi.provideOkHttpClient
import br.com.lucolimac.pokedex.data.PokemonApi.provideRetrofit
import br.com.lucolimac.pokedex.data.repository.PokedexRepositoryImpl
import br.com.lucolimac.pokedex.data.source.PokedexDataSource
import br.com.lucolimac.pokedex.domain.repository.PokedexRepository
import br.com.lucolimac.pokedex.domain.usecase.PokemonListUseCase
import br.com.lucolimac.pokedex.domain.usecase.PokemonListUseCaseImpl
import br.com.lucolimac.pokedex.framework.data.source.PokedexDataSourceImpl
import br.com.lucolimac.pokedex.ui.component.PokemonListAdapter
import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.presentation.viewmodel.ListPokemonViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal object PokedexModule {
    val pokedexModule = module {
        factory { Separator(4) }
        factory { provideOkHttpClient() }
        factory { provideRetrofit(get()) }
        factoryOf(::PokedexDataSourceImpl) { bind<PokedexDataSource>() }
        factoryOf(::PokedexRepositoryImpl) { bind<PokedexRepository>() }
        factory { Dispatchers.IO }
        factoryOf(::PokemonListUseCaseImpl) { bind<PokemonListUseCase>() }
        viewModelOf(::ListPokemonViewModel)
        factoryOf(::PokemonListAdapter)
    }
}