package br.com.lucolimac.pokedex.domain.entity

import br.com.lucolimac.pokedex.domain.util.Constants

internal data class Pokemon(
    val name: String,
    val number: Int,
    val types: List<String>,
    val moves: List<String>,
    val weight: Double,
    val height: Double
) {
    val imageUrl: String = Constants.URL_BASE_IMAGE.format(number)
}
