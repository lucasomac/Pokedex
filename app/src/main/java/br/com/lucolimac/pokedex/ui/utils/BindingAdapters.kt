package br.com.lucolimac.pokedex.ui.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.pokedex.ui.component.PokemonListAdapter
import com.bumptech.glide.Glide


//    @BindingAdapter("separator")
//    fun RecyclerView.separator(separator: Separator?) {
//        separator?.let { this.addItemDecoration(it) }
//    }

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("android:visibility")
fun View.setVisibility(value: Boolean) {
    this.visibility = if (value) View.VISIBLE else View.GONE
}

//@BindingAdapter("data")
//internal suspend fun RecyclerView.attachList(
//    data: PagingData<PokemonList.PokemonResume>
//) {
//    this.adapter.apply { submitData(data) }
//}

@BindingAdapter("adapter")
internal fun RecyclerView.setAdapter(adapter: PokemonListAdapter) {
    this.adapter = adapter
}