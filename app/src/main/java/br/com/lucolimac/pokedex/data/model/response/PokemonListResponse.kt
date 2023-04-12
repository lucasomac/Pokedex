package br.com.lucolimac.pokedex.data.model.response


import br.com.lucolimac.pokedex.domain.entity.Pokedex
import com.google.gson.annotations.SerializedName

internal data class PokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PokemonResumeResponse>,
) {
    fun toEntity(): Pokedex {
        return Pokedex(previous, next, results.map { it.toEntity() })
    }

    data class PokemonResumeResponse(
        @SerializedName("name") val name: String, @SerializedName("url") val url: String
    ) {
        fun toEntity(): Pokedex.PokemonResume {
            return Pokedex.PokemonResume(
                this.name,
                this.url.split("pokemon/").last().replace("/", "").toLong()
            )
        }
    }
}