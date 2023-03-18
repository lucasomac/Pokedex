package br.com.lucolimac.pokedex.data

import android.content.Context
import android.util.Log
import br.com.lucolimac.pokedex.data.service.PokedexService
import br.com.lucolimac.pokedex.data.util.Constants.POKE_API_HOST
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object PokemonApi {
    private const val OK_HTTP = "POKEMON-LOG"


    fun provideOkHttpClient(context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor { Log.i(OK_HTTP, it) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(POKE_API_HOST).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun provideUserService(retrofit: Retrofit): PokedexService {
        return retrofit.create(PokedexService::class.java)
    }
}