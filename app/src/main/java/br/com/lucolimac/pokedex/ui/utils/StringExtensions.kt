package br.com.lucolimac.pokedex.ui.utils

import java.util.*

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

    fun String.capitalize(): String {
        return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}