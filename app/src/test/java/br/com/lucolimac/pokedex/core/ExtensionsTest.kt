package br.com.lucolimac.pokedex.core

import br.com.lucolimac.pokedex.core.Extensions.metricalConversion
import br.com.lucolimac.pokedex.core.Extensions.pastaConversion
import org.junit.Assert
import org.junit.Test

class ExtensionsTest {

    @Test
    fun metricalConversion() {
        val value = 10
        val base = 100
        val expected = 0.01
        val actual = value.metricalConversion(base)
        Assert.assertEquals(expected, actual, 0.0)
    }

    @Test
    fun pastaConversion() {
        val value = 10
        val base = 100
        val expected = 0.01
        val actual = value.pastaConversion(base)
        Assert.assertEquals(expected, actual, 0.0)
    }
}