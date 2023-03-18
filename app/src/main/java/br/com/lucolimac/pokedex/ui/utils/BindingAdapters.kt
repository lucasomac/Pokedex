package br.com.lucolimac.pokedex.ui.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.pokedex.ui.component.Separator

internal object BindingAdapters {
    @BindingAdapter("separator")
    fun RecyclerView.separator(separator: Separator) {
        this.addItemDecoration(separator)
    }
}