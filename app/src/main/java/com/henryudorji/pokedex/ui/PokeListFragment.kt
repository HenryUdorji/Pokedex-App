package com.henryudorji.pokedex.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.henryudorji.pokedex.R
import com.henryudorji.pokedex.databinding.FragmentPokeListBinding
import com.henryudorji.pokedex.ui.base.BaseFragment
import com.henryudorji.pokedex.ui.viewmodel.PokeViewModel
import com.henryudorji.pokedex.utils.*
import com.henryudorji.pokedex.utils.Constants.ANIMATION_DURATION
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
        setHasOptionsMenu(true)
        setupUI()
    }

    private fun setupUI() {
        initPokeDexRv()
        observeNetworkConnectivity()
        observePokeDex()
    }

    private fun observePokeDex() = with(binding){
        viewModel.pokeDexLiveData.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Loading -> progressBar.show()
                is Resource.Error -> Snackbar.make(root, state.message!!, Snackbar.LENGTH_SHORT).show()
                is Resource.Success -> {
                    progressBar.hide()
                    adapter.submitList(state.data?.pokemon)
                }
            }
        }
    }

    private fun initPokeDexRv() = with(binding) {
        adapter = PokeDexRvAdapter()
        recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@PokeListFragment.adapter
            addItemDecoration(SpacesItemDecorator(10))
        }
        adapter.setOnItemClickListener { pokemon ->
            val action = PokeListFragmentDirections.actionPokeListFragmentToPokeDetailFragment(pokemon)
            findNavController().navigate(action)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiModeLiveData.observe(viewLifecycleOwner) { mode ->
                val item = menu.findItem(R.id.action_switch_ui_mode)
                item.isChecked = mode
                setUIMode(item, mode)
            }
        }

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.OnQueryTextChanged {
            //@todo search
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_switch_ui_mode) {
            item.isChecked = !item.isChecked
            setUIMode(item, item.isChecked)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUIMode(item: MenuItem, isChecked: Boolean) {
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            viewModel.saveUIMode(true)
            item.setIcon(R.drawable.ic_night)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            viewModel.saveUIMode(false)
            item.setIcon(R.drawable.ic_day)
        }
    }
}