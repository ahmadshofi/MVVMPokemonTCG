package com.ahmad.mvvm_pokemontcg

import android.app.Application
import com.ahmad.mvvm_pokemontcg.datastore.card.PokemonCardLocalDataStore
import com.ahmad.mvvm_pokemontcg.datastore.card.PokemonCardRemoteDataStore
import com.ahmad.mvvm_pokemontcg.datastore.set.PokemonSetLocalDataStore
import com.ahmad.mvvm_pokemontcg.datastore.set.PokemonSetRemoteDataStore
import com.ahmad.mvvm_pokemontcg.network.RetrofitApp
import com.bumptech.glide.Glide.init
import io.github.golok.pokemontcg.repository.PokemonCardRepository
import io.github.golok.pokemontcg.repository.PokemonSetRepository


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val pokemonTcgService = RetrofitApp.POKEMON_TCG_SERVICE
        PokemonSetRepository.instance.apply {
            init(PokemonSetLocalDataStore(), PokemonSetRemoteDataStore(pokemonTcgService))
        }

        PokemonCardRepository.instance.apply {
            init(PokemonCardLocalDataStore(), PokemonCardRemoteDataStore(pokemonTcgService))
        }
    }
}
