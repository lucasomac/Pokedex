package br.com.lucolimac.pokedex.ui.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import br.com.lucolimac.pokedex.databinding.FragmentPokedexBinding
import br.com.lucolimac.pokedex.ui.adapter.PokemonListAdapter
import br.com.lucolimac.pokedex.ui.component.PokedexOnClickListener
import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.presentation.viewmodel.PokedexViewModel
import br.com.lucolimac.pokedex.ui.utils.StringExtensions.capitalize
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class PokedexFragment : PokedexOnClickListener,
    PokedexGenericFragment<FragmentPokedexBinding>(FragmentPokedexBinding::inflate) {
    private val viewModel: PokedexViewModel by viewModel()
    private val pokemonListAdapter: PokemonListAdapter by inject { parametersOf(this) }
    private val separator: Separator by inject { parametersOf(16) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this@PokedexFragment.viewModel
            separator = this@PokedexFragment.separator
            adapter = pokemonListAdapter
        }
    }

    override fun onCardPokemonClick(pokemonName: String) {
        findNavController().navigate(
            PokedexFragmentDirections.actionPokedexToPokemonFragment(
                pokemonName.capitalize()
            )
        )
    }
}