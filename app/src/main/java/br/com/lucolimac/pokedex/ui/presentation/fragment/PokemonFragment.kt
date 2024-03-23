package br.com.lucolimac.pokedex.ui.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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

class PokemonFragment : Fragment() {
    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PokemonViewModel by viewModel()
    private val bubblePokemonTypeAdapter: BubblePokemonTypeAdapter by inject()
    private val separator: Separator by inject { parametersOf(16) }
    private val args: PokemonFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}