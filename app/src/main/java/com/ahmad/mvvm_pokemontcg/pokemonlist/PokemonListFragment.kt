package com.ahmad.mvvm_pokemontcg.pokemonlist

import PokemonListAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.ahmad.mvvm_pokemontcg.R
import com.ahmad.mvvm_pokemontcg.model.PokemonCard
import io.github.golok.pokemontcg.repository.PokemonCardRepository
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PokemonListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class PokemonListFragment : Fragment() {
    private lateinit var vm: PokemonListViewModel
    private lateinit var adapter: PokemonListAdapter

    private val args: PokemonListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PokemonListAdapter()
        rvCard.adapter = adapter

        val factory = PokemonListViewModelFactory(PokemonCardRepository.instance)
        vm = ViewModelProviders.of(this, factory).get(PokemonListViewModel::class.java).apply {
            viewState.observe(
                this@PokemonListFragment,
                Observer(this@PokemonListFragment::handleState)
            )
            if (viewState.value?.data == null) getPokemons(args.sibapak)
            srlCard.setOnRefreshListener { getPokemons(args.sibapak) }
        }
    }

    private fun handleState(viewState: PokemonListViewState?) {
        viewState?.let {
            toggleLoading(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }

    private fun showData(data: MutableList<PokemonCard>) {
        adapter.updateData(data)
        tvCardError.visibility = View.GONE
        rvCard.visibility = View.VISIBLE
    }

    private fun showError(error: Exception) {
        tvCardError.text = error.message
        tvCardError.visibility = View.VISIBLE
        rvCard.visibility = View.GONE
    }

    private fun toggleLoading(loading: Boolean) {
        srlCard.isRefreshing = loading
    }
}