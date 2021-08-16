package com.henryudorji.pokedex.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.henryudorji.pokedex.R
import com.henryudorji.pokedex.data.model.Pokemon
import com.henryudorji.pokedex.databinding.FragmentPokeDetailBinding
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
        heightChip.text = pokemon.height
        weightChip.text = pokemon.weight

        val typeSublist: List<String> = if (pokemon.type.size > 4) {
            pokemon.type.subList(0, 3)
        }else pokemon.type
        typeSublist.forEach { type ->
            val chip = Chip(requireContext())
            chip.apply {
                text = type
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setTextAppearanceResource(R.style.BodyTextView)
            }
            typeChipLayout.apply {
                addView(chip)
                setHorizontalGravity(Gravity.CENTER_HORIZONTAL)
            }
        }

        val weaknessSublist: List<String> = if (pokemon.weaknesses.size > 4) {
            pokemon.weaknesses.subList(0, 3)
        }else pokemon.weaknesses
        weaknessSublist.forEach { weakness ->
            val chip = Chip(requireContext())
            chip.apply {
                text = weakness
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setTextAppearanceResource(R.style.BodyTextView)
            }
            weaknessChipLayout.apply {
                addView(chip)
                setHorizontalGravity(Gravity.CENTER_HORIZONTAL)
            }
        }
    }
}