package com.henryudorji.pokedex.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat.animate
import androidx.fragment.app.activityViewModels
import com.henryudorji.pokedex.R
import com.henryudorji.pokedex.databinding.FragmentPokeListBinding
import com.henryudorji.pokedex.ui.base.BaseFragment
import com.henryudorji.pokedex.ui.viewmodel.PokeViewModel
import com.henryudorji.pokedex.utils.Constants.ANIMATION_DURATION
import com.henryudorji.pokedex.utils.NetworkUtils
import dagger.hilt.android.AndroidEntryPoint
import www.thecodemonks.techbytes.utils.*

@AndroidEntryPoint
class PokeListFragment: BaseFragment<FragmentPokeListBinding, PokeViewModel>() {
    override val viewModel: PokeViewModel by activityViewModels()

    private lateinit var adapter: PokeDexRvAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPokeListBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        initPokeDexRv()
        observeNetworkConnectivity()
        observePokeDex()
    }

    private fun observePokeDex() {
        viewModel.pokeDexLiveData.observe(viewLifecycleOwner) { pokeDex ->
            adapter.submitList(pokeDex.pokemon)
        }
    }

    private fun initPokeDexRv() = with(binding) {
        adapter = PokeDexRvAdapter()
        recyclerView.apply {
            adapter = adapter
            addItemDecoration(SpacesItemDecorator(10))
        }
    }

    private fun observeNetworkConnectivity() {
        NetworkUtils.observeConnectivity(requireContext())
            .observe(viewLifecycleOwner) { isConnected ->
                if (isConnected) onConnectivityAvailable() else onConnectivityUnavailable()
            }
    }

    private fun onConnectivityAvailable() = with(binding) {
        textNetworkStatus.apply {
            text = getString(R.string.text_connectivity)
            setDrawableLeft(R.drawable.ic_internet_on)
        }
        containerNetworkStatus.apply {
            setBackgroundColor(
                context.getColorCompat(R.color.colorStatusConnected)
            )
            animate()
                .alpha(1f)
                .setStartDelay(ANIMATION_DURATION)
                .setDuration(ANIMATION_DURATION)
                .setListener(
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            hide()
                        }
                    }
                )
                .start()
        }
    }

    private fun onConnectivityUnavailable() = with(binding) {
        textNetworkStatus.apply {
            text = getString(R.string.text_no_connectivity)
            setDrawableLeft(R.drawable.ic_internet_off)
        }
        containerNetworkStatus.apply {
            show()
            setBackgroundColor(
                context.getColorCompat(R.color.colorStatusNotConnected)
            )
        }
    }
}