package br.com.lucolimac.pokedex.core

object Extensions {
    fun Int.metricalConversion(base: Int): Double {
        return this / (10.0 * base)
    }
    fun Int.pastaConversion(base: Int): Double {
        return this / (10.0 * base)
    }
}