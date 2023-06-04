package br.com.lucolimac.pokedex.ui.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
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
import com.google.android.material.circularreveal.cardview.CircularRevealCardView
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
internal fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url).placeholder(R.drawable.pokeball).into(this)
}

@BindingAdapter("isLoadingPage", "context", requireAll = true)
internal fun CircularProgressIndicator.isLoadingPage(
    adapter: PokemonListAdapter, context: Context
) {
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

@BindingAdapter("adapter")
internal fun RecyclerView.adapter(adapter: RecyclerView.Adapter<*>?) {
    adapter?.let {
        this.adapter = it
    }
}

@BindingAdapter("pageData", "context")
internal fun RecyclerView.listData(
    pageData: Flow<PagingData<PokemonResume>>, context: Context
) {
    val adapter = this.adapter as PokemonListAdapter
    (context as LifecycleOwner).lifecycleScope.launch {
        pageData.collectLatest {
            adapter.submitData(it)
        }
    }
}

@BindingAdapter("data")
internal fun RecyclerView.data(data: List<String>?) {
    if (this.adapter == null) {
        val adapter = BubblePokemonTypeAdapter()
        adapter.submitList(data?.map { it.capitalize() })
    } else {
        val adapter = this.adapter as BubblePokemonTypeAdapter
        adapter.submitList(data?.map { it.capitalize() })
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
internal fun View.pokemonType(pokemonType: String?, context: Context) {
    if (this is CircularRevealCardView) {
        this.setCardBackgroundColor(ContextCompat.getColor(context, pokemonType.getBackground()))
    } else {
        this.background = AppCompatResources.getDrawable(context, pokemonType.getBackground())
    }
}
