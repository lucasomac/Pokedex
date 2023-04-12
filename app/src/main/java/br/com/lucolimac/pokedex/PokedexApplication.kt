package br.com.lucolimac.pokedex

import android.app.Application
import br.com.lucolimac.pokedex.framework.di.PokedexModule.pokedexModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

internal class PokedexApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PokedexApplication)
            modules(listOf(pokedexModule))
        }
    }
}