package br.com.lucolimac.pokedex.data.model.response


import br.com.lucolimac.pokedex.core.Extensions.metricalConversion
import br.com.lucolimac.pokedex.core.Extensions.pastaConversion
import br.com.lucolimac.pokedex.domain.entity.Pokemon
import com.google.gson.annotations.SerializedName

internal data class PokemonResponse(
    @SerializedName("abilities") val abilities: List<Ability>,
    @SerializedName("base_experience") val baseExperience: Int, // 243
    @SerializedName("forms") val forms: List<Form>,
    @SerializedName("game_indices") val gameIndices: List<GameIndice>,
    @SerializedName("height") val height: Int, // 8
    @SerializedName("held_items") val heldItems: List<HeldItem>,
    @SerializedName("id") val id: Int, // 26
    @SerializedName("is_default") val isDefault: Boolean, // true
    @SerializedName("location_area_encounters") val locationAreaEncounters: String, // https://pokeapi.co/api/v2/pokemon/26/encounters
    @SerializedName("moves") val moves: List<Move>,
    @SerializedName("name") val name: String, // raichu
    @SerializedName("order") val order: Int, // 51
    @SerializedName("past_types") val pastTypes: List<Any>,
    @SerializedName("species") val species: Species,
    @SerializedName("sprites") val sprites: Sprites,
    @SerializedName("stats") val stats: List<Stat>,
    @SerializedName("types") val types: List<Type>,
    @SerializedName("weight") val weight: Int // 300
) {
    fun toEntity(): Pokemon {
        return Pokemon(
            this.name,
            this.id,
            this.types.map { it.type.name },
            this.moves.map { it.move.name },
            this.weight.pastaConversion(1),
            this.height.metricalConversion(1)
        )
    }

    data class Ability(
        @SerializedName("ability") val ability: Ability,
        @SerializedName("is_hidden") val isHidden: Boolean, // false
        @SerializedName("slot") val slot: Int // 1
    ) {
        data class Ability(
            @SerializedName("name") val name: String, // static
            @SerializedName("url") val url: String // https://pokeapi.co/api/v2/ability/9/
        )
    }

    data class Form(
        @SerializedName("name") val name: String, // raichu
        @SerializedName("url") val url: String // https://pokeapi.co/api/v2/pokemon-form/26/
    )

    data class GameIndice(
        @SerializedName("game_index") val gameIndex: Int, // 85
        @SerializedName("version") val version: Version
    ) {
        data class Version(
            @SerializedName("name") val name: String, // red
            @SerializedName("url") val url: String // https://pokeapi.co/api/v2/version/1/
        )
    }

    data class HeldItem(
        @SerializedName("item") val item: Item,
        @SerializedName("version_details") val versionDetails: List<VersionDetail>
    ) {
        data class Item(
            @SerializedName("name") val name: String, // oran-berry
            @SerializedName("url") val url: String // https://pokeapi.co/api/v2/item/132/
        )

        data class VersionDetail(
            @SerializedName("rarity") val rarity: Int, // 5
            @SerializedName("version") val version: Version
        ) {
            data class Version(
                @SerializedName("name") val name: String, // ruby
                @SerializedName("url") val url: String // https://pokeapi.co/api/v2/version/7/
            )
        }
    }

    data class Move(
        @SerializedName("move") val move: Move,
        @SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetail>
    ) {
        data class Move(
            @SerializedName("name") val name: String, // mega-punch
            @SerializedName("url") val url: String // https://pokeapi.co/api/v2/move/5/
        )

        data class VersionGroupDetail(
            @SerializedName("level_learned_at") val levelLearnedAt: Int, // 0
            @SerializedName("move_learn_method") val moveLearnMethod: MoveLearnMethod,
            @SerializedName("version_group") val versionGroup: VersionGroup
        ) {
            data class MoveLearnMethod(
                @SerializedName("name") val name: String, // machine
                @SerializedName("url") val url: String // https://pokeapi.co/api/v2/move-learn-method/4/
            )

            data class VersionGroup(
                @SerializedName("name") val name: String, // red-blue
                @SerializedName("url") val url: String // https://pokeapi.co/api/v2/version-group/1/
            )
        }
    }

    data class Species(
        @SerializedName("name") val name: String, // raichu
        @SerializedName("url") val url: String // https://pokeapi.co/api/v2/pokemon-species/26/
    )

    data class Sprites(
        @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/26.png
        @SerializedName("back_female") val backFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/female/26.png
        @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/26.png
        @SerializedName("back_shiny_female") val backShinyFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/female/26.png
        @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/26.png
        @SerializedName("front_female") val frontFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/female/26.png
        @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/26.png
        @SerializedName("front_shiny_female") val frontShinyFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/female/26.png
        @SerializedName("other") val other: Other,
        @SerializedName("versions") val versions: Versions
    ) {
        data class Other(
            @SerializedName("dream_world") val dreamWorld: DreamWorld,
            @SerializedName("home") val home: Home,
            @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
        ) {
            data class DreamWorld(
                @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/26.svg
                @SerializedName("front_female") val frontFemale: Any? // null
            )

            data class Home(
                @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/26.png
                @SerializedName("front_female") val frontFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/female/26.png
                @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/26.png
                @SerializedName("front_shiny_female") val frontShinyFemale: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/shiny/female/26.png
            )

            data class OfficialArtwork(
                @SerializedName("front_default") val frontDefault: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/26.png
            )
        }

        data class Versions(
            @SerializedName("generation-i") val generationI: GenerationI,
            @SerializedName("generation-ii") val generationIi: GenerationIi,
            @SerializedName("generation-iii") val generationIii: GenerationIii,
            @SerializedName("generation-iv") val generationIv: GenerationIv,
            @SerializedName("generation-v") val generationV: GenerationV,
            @SerializedName("generation-vi") val generationVi: GenerationVi,
            @SerializedName("generation-vii") val generationVii: GenerationVii,
            @SerializedName("generation-viii") val generationViii: GenerationViii
        ) {
            data class GenerationI(
                @SerializedName("red-blue") val redBlue: RedBlue,
                @SerializedName("yellow") val yellow: Yellow
            ) {
                data class RedBlue(
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/26.png
                    @SerializedName("back_gray") val backGray: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/back/gray/26.png
                    @SerializedName("back_transparent") val backTransparent: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/transparent/back/26.png
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/26.png
                    @SerializedName("front_gray") val frontGray: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/gray/26.png
                    @SerializedName("front_transparent") val frontTransparent: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/red-blue/transparent/26.png
                )

                data class Yellow(
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/back/26.png
                    @SerializedName("back_gray") val backGray: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/back/gray/26.png
                    @SerializedName("back_transparent") val backTransparent: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/transparent/back/26.png
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/26.png
                    @SerializedName("front_gray") val frontGray: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/gray/26.png
                    @SerializedName("front_transparent") val frontTransparent: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-i/yellow/transparent/26.png
                )
            }

            data class GenerationIi(
                @SerializedName("crystal") val crystal: Crystal,
                @SerializedName("gold") val gold: Gold,
                @SerializedName("silver") val silver: Silver
            ) {
                data class Crystal(
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/back/26.png
                    @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/back/shiny/26.png
                    @SerializedName("back_shiny_transparent") val backShinyTransparent: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/transparent/back/shiny/26.png
                    @SerializedName("back_transparent") val backTransparent: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/transparent/back/26.png
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/26.png
                    @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/shiny/26.png
                    @SerializedName("front_shiny_transparent") val frontShinyTransparent: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/transparent/shiny/26.png
                    @SerializedName("front_transparent") val frontTransparent: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/crystal/transparent/26.png
                )

                data class Gold(
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/back/26.png
                    @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/back/shiny/26.png
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/26.png
                    @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/shiny/26.png
                    @SerializedName("front_transparent") val frontTransparent: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/gold/transparent/26.png
                )

                data class Silver(
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/back/26.png
                    @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/back/shiny/26.png
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/26.png
                    @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/shiny/26.png
                    @SerializedName("front_transparent") val frontTransparent: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-ii/silver/transparent/26.png
                )
            }

            data class GenerationIii(
                @SerializedName("emerald") val emerald: Emerald,
                @SerializedName("firered-leafgreen") val fireredLeafgreen: FireredLeafgreen,
                @SerializedName("ruby-sapphire") val rubySapphire: RubySapphire
            ) {
                data class Emerald(
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/26.png
                    @SerializedName("front_shiny") val frontShiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/shiny/26.png
                )

                data class FireredLeafgreen(
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/back/26.png
                    @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/back/shiny/26.png
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/26.png
                    @SerializedName("front_shiny") val frontShiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/firered-leafgreen/shiny/26.png
                )

                data class RubySapphire(
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/back/26.png
                    @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/back/shiny/26.png
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/26.png
                    @SerializedName("front_shiny") val frontShiny: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/ruby-sapphire/shiny/26.png
                )
            }

            data class GenerationIv(
                @SerializedName("diamond-pearl") val diamondPearl: DiamondPearl,
                @SerializedName("heartgold-soulsilver") val heartgoldSoulsilver: HeartgoldSoulsilver,
                @SerializedName("platinum") val platinum: Platinum
            ) {
                data class DiamondPearl(
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/back/26.png
                    @SerializedName("back_female") val backFemale: Any?, // null
                    @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/back/shiny/26.png
                    @SerializedName("back_shiny_female") val backShinyFemale: Any?, // null
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/26.png
                    @SerializedName("front_female") val frontFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/female/26.png
                    @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/shiny/26.png
                    @SerializedName("front_shiny_female") val frontShinyFemale: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/diamond-pearl/shiny/female/26.png
                )

                data class HeartgoldSoulsilver(
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/back/26.png
                    @SerializedName("back_female") val backFemale: Any?, // null
                    @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/back/shiny/26.png
                    @SerializedName("back_shiny_female") val backShinyFemale: Any?, // null
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/26.png
                    @SerializedName("front_female") val frontFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/female/26.png
                    @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/shiny/26.png
                    @SerializedName("front_shiny_female") val frontShinyFemale: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/heartgold-soulsilver/shiny/female/26.png
                )

                data class Platinum(
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/back/26.png
                    @SerializedName("back_female") val backFemale: Any?, // null
                    @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/back/shiny/26.png
                    @SerializedName("back_shiny_female") val backShinyFemale: Any?, // null
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/26.png
                    @SerializedName("front_female") val frontFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/female/26.png
                    @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/shiny/26.png
                    @SerializedName("front_shiny_female") val frontShinyFemale: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iv/platinum/shiny/female/26.png
                )
            }

            data class GenerationV(
                @SerializedName("black-white") val blackWhite: BlackWhite
            ) {
                data class BlackWhite(
                    @SerializedName("animated") val animated: Animated,
                    @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/26.png
                    @SerializedName("back_female") val backFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/female/26.png
                    @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/shiny/26.png
                    @SerializedName("back_shiny_female") val backShinyFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/back/shiny/female/26.png
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/26.png
                    @SerializedName("front_female") val frontFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/female/26.png
                    @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/shiny/26.png
                    @SerializedName("front_shiny_female") val frontShinyFemale: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/shiny/female/26.png
                ) {
                    data class Animated(
                        @SerializedName("back_default") val backDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/26.gif
                        @SerializedName("back_female") val backFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/female/26.gif
                        @SerializedName("back_shiny") val backShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/shiny/26.gif
                        @SerializedName("back_shiny_female") val backShinyFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/back/shiny/female/26.gif
                        @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/26.gif
                        @SerializedName("front_female") val frontFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/female/26.gif
                        @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/shiny/26.gif
                        @SerializedName("front_shiny_female") val frontShinyFemale: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/shiny/female/26.gif
                    )
                }
            }

            data class GenerationVi(
                @SerializedName("omegaruby-alphasapphire") val omegarubyAlphasapphire: OmegarubyAlphasapphire,
                @SerializedName("x-y") val xY: XY
            ) {
                data class OmegarubyAlphasapphire(
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/26.png
                    @SerializedName("front_female") val frontFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/female/26.png
                    @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/shiny/26.png
                    @SerializedName("front_shiny_female") val frontShinyFemale: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/omegaruby-alphasapphire/shiny/female/26.png
                )

                data class XY(
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/26.png
                    @SerializedName("front_female") val frontFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/female/26.png
                    @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/shiny/26.png
                    @SerializedName("front_shiny_female") val frontShinyFemale: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vi/x-y/shiny/female/26.png
                )
            }

            data class GenerationVii(
                @SerializedName("icons") val icons: Icons,
                @SerializedName("ultra-sun-ultra-moon") val ultraSunUltraMoon: UltraSunUltraMoon
            ) {
                data class Icons(
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/icons/26.png
                    @SerializedName("front_female") val frontFemale: Any? // null
                )

                data class UltraSunUltraMoon(
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/26.png
                    @SerializedName("front_female") val frontFemale: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/female/26.png
                    @SerializedName("front_shiny") val frontShiny: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/shiny/26.png
                    @SerializedName("front_shiny_female") val frontShinyFemale: String // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-vii/ultra-sun-ultra-moon/shiny/female/26.png
                )
            }

            data class GenerationViii(
                @SerializedName("icons") val icons: Icons
            ) {
                data class Icons(
                    @SerializedName("front_default") val frontDefault: String, // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-viii/icons/26.png
                    @SerializedName("front_female") val frontFemale: Any? // null
                )
            }
        }
    }

    data class Stat(
        @SerializedName("base_stat") val baseStat: Int, // 60
        @SerializedName("effort") val effort: Int, // 0
        @SerializedName("stat") val stat: Stat
    ) {
        data class Stat(
            @SerializedName("name") val name: String, // hp
            @SerializedName("url") val url: String // https://pokeapi.co/api/v2/stat/1/
        )
    }

    data class Type(
        @SerializedName("slot") val slot: Int, // 1
        @SerializedName("type") val type: Type
    ) {
        data class Type(
            @SerializedName("name") val name: String, // electric
            @SerializedName("url") val url: String // https://pokeapi.co/api/v2/type/13/
        )
    }
}