package br.com.lucolimac.pokedex.data.model.response


import br.com.lucolimac.pokedex.domain.entity.PokemonList
import com.google.gson.annotations.SerializedName

internal data class PokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String,
    @SerializedName("previous") val previous: Any?,
    @SerializedName("results") val results: List<PokemonResumeResponse>,
) {
    fun toEntity(): PokemonList {
        return PokemonList(results.map { it.toEntity() })
    }

    data class PokemonResumeResponse(
        @SerializedName("name") val name: String, @SerializedName("url") val url: String
    ) {
        fun toEntity(): PokemonList.PokemonResume {
            return PokemonList.PokemonResume(this.name, this.url)
        }
    }
}