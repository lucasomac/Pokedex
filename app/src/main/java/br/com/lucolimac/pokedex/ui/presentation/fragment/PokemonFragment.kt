package br.com.lucolimac.pokedex.ui.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.lucolimac.pokedex.R
import br.com.lucolimac.pokedex.databinding.FragmentPokemonBinding
import br.com.lucolimac.pokedex.ui.presentation.viewmodel.PokedexViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonFragment : Fragment() {

    private var _binding: FragmentPokemonBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PokedexViewModel by viewModel()
    private val args: PokemonFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pokemon, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pokemonName = args.pokemonName
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}