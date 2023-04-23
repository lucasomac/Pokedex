package br.com.lucolimac.pokedex.ui.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.lucolimac.pokedex.R
import br.com.lucolimac.pokedex.databinding.FragmentPokedexBinding
import br.com.lucolimac.pokedex.ui.component.PokedexOnClickListener
import br.com.lucolimac.pokedex.ui.adapter.PokemonListAdapter
import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.presentation.viewmodel.PokedexViewModel
import br.com.lucolimac.pokedex.ui.utils.StringExtensions.capitalize
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class PokedexFragment : Fragment(), PokedexOnClickListener {

    private var _binding: FragmentPokedexBinding? = null
    private val viewModel: PokedexViewModel by viewModel()
    private val pokemonListAdapter: PokemonListAdapter by inject { parametersOf(this) }
    private val separator: Separator by inject { parametersOf(16) }
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokedex, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@PokedexFragment.viewModel
            separator = this@PokedexFragment.separator
            adapter = pokemonListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCardPokemonClick(pokemonName: String) {
        findNavController().navigate(PokedexFragmentDirections.actionPokedexToPokemonFragment(pokemonName.capitalize()))
    }
}