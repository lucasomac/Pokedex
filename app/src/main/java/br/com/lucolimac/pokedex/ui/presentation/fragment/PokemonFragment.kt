package br.com.lucolimac.pokedex.ui.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.lucolimac.pokedex.R
import br.com.lucolimac.pokedex.databinding.FragmentPokemonBinding
import br.com.lucolimac.pokedex.ui.adapter.BubblePokemonTypeAdapter
import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.presentation.viewmodel.PokemonViewModel
import br.com.lucolimac.pokedex.ui.utils.StringExtensions.deCapitalize
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
            composeViewAttributes.setContent {
                Row {
                    Text(
                        text = "10 Kilos",
                        style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center)
                    )
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