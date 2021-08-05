package com.henryudorji.pokedex.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.henryudorji.pokedex.databinding.FragmentPokeListBinding
import com.henryudorji.pokedex.ui.base.BaseFragment
import com.henryudorji.pokedex.ui.viewmodel.PokeViewModel


class PokeListFragment: BaseFragment<FragmentPokeListBinding, PokeViewModel>() {
    override val viewModel: PokeViewModel by activityViewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPokeListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}