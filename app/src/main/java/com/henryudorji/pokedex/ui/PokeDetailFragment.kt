package com.henryudorji.pokedex.ui

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.henryudorji.pokedex.data.model.Pokemon
import com.henryudorji.pokedex.databinding.FragmentPokeDetailBinding
import com.henryudorji.pokedex.databinding.FragmentPokeListBinding
import com.henryudorji.pokedex.ui.base.BaseFragment
import com.henryudorji.pokedex.ui.viewmodel.PokeViewModel
import com.squareup.picasso.Picasso


class PokeDetailFragment: BaseFragment<FragmentPokeDetailBinding, PokeViewModel>() {
    override val viewModel: PokeViewModel by activityViewModels()
    private val args: PokeDetailFragmentArgs by navArgs()
    private lateinit var pokemon: Pokemon

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPokeDetailBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemon = args.pokemon

        setupUI()
    }

    private fun setupUI() = with(binding) {
        Picasso.get()
            .load(pokemon.img).into(pokeImg)
        title.text = pokemon.name
    }
}