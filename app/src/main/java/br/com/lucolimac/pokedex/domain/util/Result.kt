package br.com.lucolimac.pokedex.domain.util

internal sealed class Result<out S, out E> {
    data class Success<S> constructor(
        val data: S
    ) : Result<S, Nothing>()

    data class Error<E> constructor(
        val data: E
    ) : Result<Nothing, E>()

    data class Failure constructor(
        val throwable: Throwable? = null
    ) : Result<Nothing, Nothing>()
}
