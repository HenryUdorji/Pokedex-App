package com.henryudorji.pokedex.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.henryudorji.pokedex.R
import com.henryudorji.pokedex.data.model.Pokemon
import com.henryudorji.pokedex.databinding.RvPokedexItemBinding
import com.henryudorji.pokedex.ui.viewmodel.PokeViewModel
import com.squareup.picasso.Picasso


class PokeDexRvAdapter:
    ListAdapter<Pokemon, PokeDexRvAdapter.PokeDexViewHolder>(PokeDexComparator()) {

        inner class PokeDexViewHolder(private val binding: RvPokedexItemBinding):
            RecyclerView.ViewHolder(binding.root) {

                fun bind(pokemon: Pokemon) = with(binding){
                    Picasso.get()
                        .load(pokemon.img)
                        .placeholder(R.drawable.placeholder_cover_sized)
                        .into(pokeImg)

                    pokeNameTv.text = pokemon.name

                    // on item click
                    root.setOnClickListener {
                        onItemClickListener?.let { it(pokemon) }
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeDexViewHolder {
        val binding = RvPokedexItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokeDexViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokeDexViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PokeDexComparator: DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) =
            oldItem == newItem

    }

    // on item click listener
    private var onItemClickListener: ((Pokemon) -> Unit)? = null

    fun setOnItemClickListener(listener: (Pokemon) -> Unit) {
        onItemClickListener = listener
    }
}