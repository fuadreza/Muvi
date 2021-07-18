package io.github.fuadreza.muvi.presentation.genre

import io.github.fuadreza.core_android.abstraction.BaseRecyclerViewAdapter
import io.github.fuadreza.muvi.R
import io.github.fuadreza.muvi.databinding.ItemGenreBinding
import io.github.fuadreza.muvi.domain.entity.MovieGenre

class MovieGenreAdapter(
    private val itemClickCallback: ((MovieGenre) -> Unit)?
): BaseRecyclerViewAdapter<MovieGenre, ItemGenreBinding>() {
    override fun getLayout(): Int = R.layout.item_genre

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemGenreBinding>,
        position: Int
    ) {
        holder.binding.data = items[position]
        holder.binding.root.setOnClickListener {
            holder.binding.data?.let {
                itemClickCallback?.invoke(it)
            }
        }
    }
}