package br.com.lucolimac.pokedex.domain.entity

internal class PokemonList(private val listOfPokemonResume: List<PokemonResume>) {
    data class PokemonResume(private val name: String, private val link: String)
}