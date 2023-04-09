package br.com.lucolimac.pokedex.domain.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.lucolimac.pokedex.domain.entity.Pokedex
import br.com.lucolimac.pokedex.domain.usecase.PokedexUseCase
import br.com.lucolimac.pokedex.domain.util.Result

internal class PokemonResumePagingSource(
    private val useCase: PokedexUseCase, private val offset: Int, private val limit: Int
) : PagingSource<Int, Pokedex.PokemonResume>() {
    private lateinit var data: Result<Pokedex, String>
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokedex.PokemonResume> {
        val currentPage = params.key ?: offset
        useCase(currentPage, limit).collect {
            data = it
        }
        return when (data) {
            is Result.Success -> {
                val list = (data as Result.Success<Pokedex>).data
                LoadResult.Page(
                    data = list.listOfPokemonResume,
                    prevKey = if (list.previous.isNullOrEmpty()) null else currentPage.minus(
                        limit
                    ),
                    nextKey = if (list.next.isNullOrEmpty()) null else currentPage.plus(limit)
                )
            }
            is Result.Error -> LoadResult.Invalid()
            is Result.Failure -> LoadResult.Error((data as Result.Failure).throwable!!)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokedex.PokemonResume>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(limit)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(
                    limit
                )
        }
    }
}