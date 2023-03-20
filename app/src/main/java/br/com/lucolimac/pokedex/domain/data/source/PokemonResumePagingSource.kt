package br.com.lucolimac.pokedex.domain.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.lucolimac.pokedex.domain.entity.PokemonList
import br.com.lucolimac.pokedex.ui.presentation.state.ListPokemonState
import retrofit2.HttpException
import java.io.IOException

internal class PokemonResumePagingSource(
    private val data: ListPokemonState.Success,
    private val offset: Int,
    private val limit: Int
) : PagingSource<Int, PokemonList.PokemonResume>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonList.PokemonResume> {

        val currentPage = params.key ?: offset
        return try {
            LoadResult.Page(
                data = data.pokemonList.listOfPokemonResume,
                prevKey = if (data.pokemonList.previous.isNullOrEmpty()) null else currentPage.minus(limit),
                nextKey = if (data.pokemonList.next.isNullOrEmpty()) null else currentPage.plus(limit)
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
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