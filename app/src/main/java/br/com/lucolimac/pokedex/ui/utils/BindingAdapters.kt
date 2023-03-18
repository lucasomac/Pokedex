package br.com.lucolimac.pokedex.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.pokedex.ui.component.Separator
import com.bumptech.glide.Glide

internal object BindingAdapters {
    @BindingAdapter("separator")
    fun RecyclerView.separator(separator: Separator) {
        this.addItemDecoration(separator)
    }

    @BindingAdapter("imageUrl")
    fun ImageView.loadImage(url: String?) {
        if (url.isNullOrEmpty()) return
        Glide.with(this).load(url).into(this)
    }
}