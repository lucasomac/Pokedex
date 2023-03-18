package br.com.lucolimac.pokedex.data.util

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

internal inline fun <reified A> String.toObject(): A {
    return GsonBuilder().create().fromJson(this, object : TypeToken<A>() {}.type)
}
