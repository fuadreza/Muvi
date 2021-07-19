package io.github.fuadreza.muvi.presentation.detail.review

import io.github.fuadreza.core_android.abstraction.BaseRecyclerViewAdapter
import io.github.fuadreza.muvi.R
import io.github.fuadreza.muvi.databinding.ItemMovieReviewBinding
import io.github.fuadreza.muvi.domain.entity.ItemMovieReview

class MovieReviewAdapter(
    private val itemClickCallback: ((ItemMovieReview) -> Unit)?
) : BaseRecyclerViewAdapter<ItemMovieReview, ItemMovieReviewBinding>() {
    override fun getLayout(): Int = R.layout.item_movie_review

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<ItemMovieReviewBinding>,
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