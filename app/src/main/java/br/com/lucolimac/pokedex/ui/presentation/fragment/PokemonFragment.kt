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
import br.com.lucolimac.pokedex.databinding.FragmentPokemonBinding
import br.com.lucolimac.pokedex.ui.presentation.state.PokemonState
import br.com.lucolimac.pokedex.ui.presentation.viewmodel.PokemonViewModel
import br.com.lucolimac.pokedex.ui.utils.StringExtensions.deCapitalize
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonFragment : Fragment() {
    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PokemonViewModel by viewModel()
    private val args: PokemonFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.pokemonState.collectLatest {
                when (it) {
                    is PokemonState.Success -> {
                        binding.pokemon = it.pokemon
                    }
                    is PokemonState.Error -> {
                        binding.pokemon = null
                    }
                    else -> {
                        binding.pokemon = null
                    }
                }
            }
            viewModel.loading.collectLatest {
                when (it) {
                    true -> binding.isLoading = true
                    false -> binding.isLoading = false
                }
            }
        }
        viewModel.getPokemonByName(args.pokemonName.deCapitalize())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}