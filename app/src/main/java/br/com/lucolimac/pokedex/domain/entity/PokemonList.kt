package br.com.lucolimac.pokedex.domain.entity

import br.com.lucolimac.pokedex.domain.util.Constants.URL_BASE_IMAGE

internal class PokemonList(private val listOfPokemonResume: List<PokemonResume>) {
    data class PokemonResume(val name: String, val number: Int) {
        val imageUrl: String = URL_BASE_IMAGE.format(number)
    }
}