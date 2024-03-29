package br.com.lucolimac.pokedex.domain.entity

import br.com.lucolimac.pokedex.domain.util.Constants.URL_BASE_IMAGE

internal data class Pokedex(
    val previous: String?, val next: String?, val listOfPokemonResume: List<PokemonResume>
) {
    data class PokemonResume(val name: String, val number: Long) {
        val imageUrl: String = URL_BASE_IMAGE.format(number)
    }
}