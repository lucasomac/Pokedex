package br.com.lucolimac.pokedex.ui.utils

import android.content.Context
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.pokedex.R
import br.com.lucolimac.pokedex.domain.entity.PokemonList.PokemonResume
import br.com.lucolimac.pokedex.ui.component.PokemonListAdapter
import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.utils.StringExtensions.formatPokemonNumber
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@BindingAdapter("separator")
fun RecyclerView.separator(separator: Separator) {
    this.addItemDecoration(separator)
}

@BindingAdapter("imageUrl")
fun ShapeableImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url).placeholder(R.drawable.pokeball).into(this)
}

@BindingAdapter("isLoading", "context", requireAll = true)
internal fun CircularProgressIndicator.isLoading(adapter: PokemonListAdapter, context: Context) {
    (context as LifecycleOwner).lifecycleScope.launch {
        adapter.loadStateFlow.collectLatest {
            this@isLoading.visibility =
                if (it.refresh is LoadState.Loading) View.VISIBLE else View.GONE
        }
    }
}

@BindingAdapter("pokeAdapter")
internal fun RecyclerView.pokeAdapter(pokeAdapter: PokemonListAdapter) {
    this.adapter = pokeAdapter
}

@BindingAdapter("listData", "context")
internal fun RecyclerView.listData(
    listData: Flow<PagingData<PokemonResume>>, context: Context
) {
    val adapter = this.adapter as PokemonListAdapter
    (context as LifecycleOwner).lifecycleScope.launch {
        listData.collectLatest {
            adapter.submitData(it)
        }
    }
}

@BindingAdapter("pokemonNumber")
internal fun MaterialTextView.pokemonNumber(pokemonNumber: String) {
    this.text = pokemonNumber.formatPokemonNumber()
}

@BindingAdapter("pokemonName")
internal fun MaterialTextView.pokemonName(pokemonName: String) {
    this.text = pokemonName.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale(
                "pt",
                "BR"
            )
        ) else it.toString()
    }
}