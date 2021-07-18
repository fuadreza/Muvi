package io.github.fuadreza.muvi.presentation.discovery

import io.github.fuadreza.core_android.abstraction.BaseRecyclerViewAdapter
import io.github.fuadreza.core_android.extensions.loadBackdrop
import io.github.fuadreza.muvi.R
import io.github.fuadreza.muvi.databinding.ItemMovieDiscoveryBinding
import io.github.fuadreza.muvi.domain.entity.ItemMovieDiscovery

class MovieDiscoveryAdapter(
    private val itemClickCallback: ((ItemMovieDiscovery) -> Unit)?
): BaseRecyclerViewAdapter<ItemMovieDiscovery, ItemMovieDiscoveryBinding>() {
    override fun getLayout(): Int = R.layout.item_movie_discovery

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemMovieDiscoveryBinding>,
        position: Int
    ) {
        holder.binding.data = items[position]
        holder.binding.ivBackdrop.loadBackdrop(items[position].backdropPath)

        holder.binding.root.setOnClickListener {
            holder.binding.data?.let {
                itemClickCallback?.invoke(it)
            }
        }
    }
}