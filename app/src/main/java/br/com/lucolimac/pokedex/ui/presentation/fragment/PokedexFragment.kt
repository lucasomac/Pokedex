package br.com.lucolimac.pokedex.ui.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import br.com.lucolimac.pokedex.R
import br.com.lucolimac.pokedex.databinding.FragmentPokedexBinding
import br.com.lucolimac.pokedex.ui.component.PokemonListAdapter
import br.com.lucolimac.pokedex.ui.presentation.viewmodel.ListPokemonViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class PokedexFragment : Fragment() {

    private var _binding: FragmentPokedexBinding? = null
    private val viewModel: ListPokemonViewModel by viewModel()
    private val pokemonListAdapter: PokemonListAdapter by inject()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokedex, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.adapter = pokemonListAdapter
        binding.recyclerPokedex.adapter = pokemonListAdapter
        lifecycleScope.launchWhenStarted {
            viewModel.getListPokemon()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}