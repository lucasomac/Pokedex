package br.com.lucolimac.pokedex

import android.app.Application
import br.com.lucolimac.pokedex.framework.di.PokedexModule.pokedexModule
import org.koin.core.context.startKoin

internal class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(pokedexModule)
        }
    }
}