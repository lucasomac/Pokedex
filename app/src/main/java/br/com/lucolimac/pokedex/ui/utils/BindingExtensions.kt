package br.com.lucolimac.pokedex.ui.utils

import androidx.databinding.ViewDataBinding

internal object BindingExtensions {
    fun <T : ViewDataBinding> T.executeWithAction(action: T.() -> Unit) {
        action()
        executePendingBindings()
    }
}