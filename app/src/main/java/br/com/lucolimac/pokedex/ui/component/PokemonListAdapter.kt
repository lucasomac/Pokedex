package br.com.lucolimac.pokedex.ui.component

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.pokedex.R
import br.com.lucolimac.pokedex.databinding.CardPokemonBinding
import br.com.lucolimac.pokedex.domain.entity.PokemonList

internal class PokemonListAdapter :
    PagingDataAdapter<PokemonList.PokemonResume, PokemonListAdapter.PokemonListViewHolder>(
        DiffCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val binding = DataBindingUtil.inflate<CardPokemonBinding>(
            LayoutInflater.from(parent.context), R.layout.card_pokemon, parent, false
        )
        return PokemonListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    internal class PokemonListViewHolder(private val binding: CardPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonResume: PokemonList.PokemonResume) {
            binding.pokemonResume = pokemonResume
            binding.executePendingBindings()
        }
    }

    companion object {
        object DiffCallback : DiffUtil.ItemCallback<PokemonList.PokemonResume>() {
            override fun areItemsTheSame(
                oldItem: PokemonList.PokemonResume, newItem: PokemonList.PokemonResume
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: PokemonList.PokemonResume, newItem: PokemonList.PokemonResume
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}