package br.com.lucolimac.pokedex.domain.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.lucolimac.pokedex.domain.entity.PokemonList
import br.com.lucolimac.pokedex.domain.usecase.PokemonListUseCase
import br.com.lucolimac.pokedex.domain.util.Result

internal class PokemonResumePagingSource(
    private val useCase: PokemonListUseCase, private val offset: Int, private val limit: Int
) : PagingSource<Int, PokemonList.PokemonResume>() {
    private lateinit var data: Result<PokemonList, String>
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonList.PokemonResume> {
        val currentPage = params.key ?: offset
        useCase(currentPage, limit).collect {
            data = it
        }
        return when (data) {
            is Result.Success -> {
                val list = (data as Result.Success<PokemonList>).data
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

    override fun getRefreshKey(state: PagingState<Int, PokemonList.PokemonResume>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(limit)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(
                    limit
                )
        }
    }
}