package br.com.lucolimac.pokedex.data.util

internal object Constants {
    const val POKE_API_HOST = "https://pokeapi.co"
    const val POKE_API_BASE_PATH: String = "/api/v2/pokemon"
    const val POKE_API_POKEMON_PATH: String = "$POKE_API_BASE_PATH/{name}"
}
