package br.com.lucolimac.pokedex.ui.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

//    @BindingAdapter("separator")
//    fun RecyclerView.separator(separator: Separator?) {
//        separator?.let { this.addItemDecoration(it) }
//    }

    @BindingAdapter("imageUrl") fun ImageView.loadImage(url: String?) {
        if (url.isNullOrEmpty()) return
        Glide.with(this).load(url).into(this)
    }