package br.com.lucolimac.pokedex.ui.utils

internal object StringExtensions {
    fun String.formatPokemonNumber(): String {
        return when (this.length) {
            1 -> {
                "#00$this"
            }
            2 -> {
                "#0$this"
            }
            else -> {
                "#$this"
            }
        }
    }
}