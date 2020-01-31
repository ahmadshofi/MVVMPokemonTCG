package com.ahmad.mvvm_pokemontcg.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.golok.pokemontcg.repository.PokemonCardRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class PokemonListViewModel (private val pokemonRepository: PokemonCardRepository): ViewModel(){
    private val mViewState = MutableLiveData<PokemonListViewState>().apply {
        value = PokemonListViewState(loading = true)
    }

    val viewState: LiveData<PokemonListViewState>
    get() = mViewState

    fun getPokemons(set: Int) = viewModelScope.launch {
        try {
            val data = pokemonRepository.getPokemons(set)
            mViewState.value = mViewState.value?.copy(loading = false,error = null, data = data)
        }catch (ex : Exception)
        {
            mViewState.value = mViewState.value?.copy(loading = false,error = ex , data = null)
        }
    }
}