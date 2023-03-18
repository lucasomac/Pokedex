package br.com.lucolimac.pokedex.framework.data.source

import br.com.lucolimac.pokedex.data.model.response.PokemonListResponse
import br.com.lucolimac.pokedex.data.model.response.PokemonResponse
import br.com.lucolimac.pokedex.data.service.PokedexService
import br.com.lucolimac.pokedex.data.source.PokedexDataSource
import retrofit2.Response
import retrofit2.Retrofit

internal class PokedexDataSourceImpl(private val pokedexApi: Retrofit) : PokedexDataSource {

    private val pokedexService by lazy {
        pokedexApi.create(PokedexService::class.java)
    }

    override suspend fun getPokemonList(offset: Int, limit: Int): Response<PokemonListResponse> {
        return pokedexService.getPokemonList(offset, limit)
    }

    override suspend fun getPokemonByName(name: String): Response<PokemonResponse> {
        return pokedexService.getPokemonByName(name)
    }
}