package br.com.lucolimac.pokedex.ui.utils

import br.com.lucolimac.pokedex.R
import java.util.Locale

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

    fun String.deCapitalize(): String {
        return this.replaceFirstChar { if (it.isUpperCase()) it.lowercase(Locale.getDefault()) else it.toString() }
    }

    fun String?.getBackground(): Int {
        return when (this?.lowercase()) {
            "normal" -> R.color.type_normal
            "fighting" -> R.color.type_fighting
            "flying" -> R.color.type_flying
            "poison" -> R.color.type_poison
            "ground" -> R.color.type_ground
            "rock" -> R.color.type_rock
            "bug" -> R.color.type_bug
            "ghost" -> R.color.type_ghost
            "steel" -> R.color.type_steel
            "fire" -> R.color.type_fire
            "water" -> R.color.type_water
            "grass" -> R.color.type_grass
            "electric" -> R.color.type_electric
            "psychic" -> R.color.type_psychic
            "ice" -> R.color.type_ice
            "dragon" -> R.color.type_dragon
            "dark" -> R.color.type_dark
            "fairy" -> R.color.type_fairy
            "unknown" -> R.color.type_unknown
            "shadow" -> R.color.type_shadow
            else -> R.color.type_normal
        }
    }
}