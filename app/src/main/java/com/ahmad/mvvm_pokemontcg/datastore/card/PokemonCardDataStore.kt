package com.ahmad.mvvm_pokemontcg.datastore.card

import com.ahmad.mvvm_pokemontcg.model.PokemonCard

interface PokemonCardDataStore {
    suspend fun getPokemons(set: Int): MutableList<PokemonCard>?
    suspend fun addAll(set: Int, pokemons: MutableList<PokemonCard>?)
}