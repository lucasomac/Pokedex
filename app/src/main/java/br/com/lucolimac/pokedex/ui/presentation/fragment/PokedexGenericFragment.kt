package br.com.lucolimac.pokedex.ui.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class PokedexGenericFragment<T : ViewDataBinding>(
    private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    private var _binding: T? = null
    val binding: T get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateMethod.invoke(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}