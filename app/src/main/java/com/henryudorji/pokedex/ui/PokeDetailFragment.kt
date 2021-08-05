package com.henryudorji.pokedex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.henryudorji.pokedex.databinding.FragmentPokeDetailBinding
import com.henryudorji.pokedex.databinding.FragmentPokeListBinding
import com.henryudorji.pokedex.ui.base.BaseFragment
import com.henryudorji.pokedex.ui.viewmodel.PokeViewModel


class PokeDetailFragment: BaseFragment<FragmentPokeDetailBinding, PokeViewModel>() {
    override val viewModel: PokeViewModel by activityViewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPokeDetailBinding.inflate(inflater, container, false)
}