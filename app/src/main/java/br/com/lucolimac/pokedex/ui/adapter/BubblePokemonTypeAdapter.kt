package br.com.lucolimac.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.pokedex.R
import br.com.lucolimac.pokedex.databinding.BubblePokemonTypeBinding

internal class BubblePokemonTypeAdapter :
    ListAdapter<String, BubblePokemonTypeAdapter.BubblePokemonTypeViewHolder>(
        BubblePokemonTypeDiffUtil
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BubblePokemonTypeViewHolder {
        val binding = DataBindingUtil.inflate<BubblePokemonTypeBinding>(
            LayoutInflater.from(parent.context), R.layout.bubble_pokemon_type, parent, false
        )
        return BubblePokemonTypeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BubblePokemonTypeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    internal class BubblePokemonTypeViewHolder(private val binding: BubblePokemonTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonType: String) {
            binding.pokemonType = pokemonType
        }
    }

    companion object {
        object BubblePokemonTypeDiffUtil : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}