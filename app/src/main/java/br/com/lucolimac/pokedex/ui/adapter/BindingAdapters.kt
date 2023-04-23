package br.com.lucolimac.pokedex.ui.adapter

import android.content.Context
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.pokedex.R
import br.com.lucolimac.pokedex.domain.entity.Pokedex.PokemonResume
import br.com.lucolimac.pokedex.ui.component.Separator
import br.com.lucolimac.pokedex.ui.utils.StringExtensions.capitalize
import br.com.lucolimac.pokedex.ui.utils.StringExtensions.formatPokemonNumber
import br.com.lucolimac.pokedex.ui.utils.StringExtensions.getBackground
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@BindingAdapter("separator")
internal fun RecyclerView.separator(separator: Separator) {
    this.addItemDecoration(separator)
}

@BindingAdapter("imageUrl")
internal fun ShapeableImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url).placeholder(R.drawable.pokeball).into(this)
}

@BindingAdapter("isLoadingPage", "context", requireAll = true)
internal fun CircularProgressIndicator.isLoadingPage(adapter: PokemonListAdapter, context: Context) {
    (context as LifecycleOwner).lifecycleScope.launch {
        adapter.loadStateFlow.collectLatest {
            this@isLoadingPage.visibility =
                if (it.refresh is LoadState.Loading) View.VISIBLE else View.GONE
        }
    }
}

@BindingAdapter("isLoading")
internal fun CircularProgressIndicator.isLoading(isLoading: Boolean) {
    this@isLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
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
internal fun MaterialTextView.pokemonNumber(pokemonNumber: Long) {
    this.text = pokemonNumber.toString().formatPokemonNumber()
}

@BindingAdapter("pokemonName")
internal fun MaterialTextView.pokemonName(pokemonName: String) {
    this.text = pokemonName.capitalize()
}

@BindingAdapter("pokemonType", "context")
internal fun ConstraintLayout.pokemonType(pokemonType: String?, context: Context) {
    this.background = AppCompatResources.getDrawable(context, pokemonType.getBackground())
}
