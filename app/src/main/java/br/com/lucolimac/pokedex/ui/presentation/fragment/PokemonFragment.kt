package br.com.lucolimac.pokedex.ui.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import br.com.lucolimac.pokedex.R
import br.com.lucolimac.pokedex.attribute.domain.entity.Attribute
import br.com.lucolimac.pokedex.attribute.ui.cards.PokemonAttributesCard
import br.com.lucolimac.pokedex.databinding.FragmentPokemonBinding
import br.com.lucolimac.pokedex.ui.adapter.BubblePokemonTypeAdapter
import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.presentation.viewmodel.PokemonViewModel
import br.com.lucolimac.pokedex.ui.utils.StringExtensions.capitalize
import br.com.lucolimac.pokedex.ui.utils.StringExtensions.deCapitalize
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PokemonFragment :
    PokedexGenericFragment<FragmentPokemonBinding>(FragmentPokemonBinding::inflate) {
    private val viewModel: PokemonViewModel by viewModel()
    private val bubblePokemonTypeAdapter: BubblePokemonTypeAdapter by inject()
    private val separator: Separator by inject { parametersOf(16) }
    private val args: PokemonFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@PokemonFragment.viewModel
            this.separator = this@PokemonFragment.separator
            adapter = bubblePokemonTypeAdapter
            lifecycleScope.launch {
                viewModel?.pokemon?.collect {
                    composeViewAttributes.setContent {
                        if (it != null) {
                            PokemonAttributesCard(
                                pokemonAttributes = listOf(
                                    Attribute(
                                        R.drawable.weight, "${it.weight} Kg", "Width"
                                    ),
                                    Attribute(
                                        R.drawable.straighten, "${it.height} m", "Height"
                                    ),
                                    Attribute(
                                        R.drawable.weight,
                                        it.moves.subList(0, 2).map { it.capitalize() }.toString()
                                            .replace("[", "")
                                            .replace("]", "").replace(", ", "\n"),
                                        "Moves"
                                    ),
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPokemonByName(args.pokemonName.deCapitalize())
    }
}