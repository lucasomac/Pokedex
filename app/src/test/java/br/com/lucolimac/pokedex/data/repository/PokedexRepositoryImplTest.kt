package br.com.lucolimac.pokedex.data.repository

import br.com.lucolimac.pokedex.data.model.response.PokemonListResponse
import br.com.lucolimac.pokedex.data.source.PokedexDataSource
import br.com.lucolimac.pokedex.domain.repository.PokedexRepository
import br.com.lucolimac.pokedex.domain.util.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PokedexRepositoryImplTest {
    private lateinit var dataSource: PokedexDataSource
    private lateinit var repository: PokedexRepository

    @Before
    fun setUp() {
        dataSource = mockk()
        repository = PokedexRepositoryImpl(dataSource)
    }

    @Test
    fun `Should be return success when call get pokedex`() {
        runTest {
            val pokedexResponse = Response.success(PokemonListResponse(0, "", "", listOf()))
            coEvery { dataSource.getPokemonList(10, 10) } returns pokedexResponse

            val result = repository.getPokemonList(10, 10)
            val pokedex = Result.Success(PokemonListResponse(0, "", "", listOf()).toEntity())
            MatcherAssert.assertThat(
                result, IsInstanceOf(Result.Success::class.java)
            )
            assertEquals(pokedex.data, (result as Result.Success).data)
        }
    }
}