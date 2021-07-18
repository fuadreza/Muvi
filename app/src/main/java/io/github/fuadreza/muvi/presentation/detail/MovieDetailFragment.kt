package io.github.fuadreza.muvi.presentation.detail

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.core_android.abstraction.BaseFragment
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.core_android.extensions.loadBackdrop
import io.github.fuadreza.muvi.R
import io.github.fuadreza.muvi.databinding.FragmentMovieDetailBinding
import io.github.fuadreza.muvi.domain.entity.MovieDetail
import timber.log.Timber

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {

    override fun getViewModelClass(): Class<MovieDetailViewModel> = MovieDetailViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_detail

    companion object {
        const val ARG_KEY_MOVIE_ID = "movieId"
    }

    private var movieId: String? = null

    override fun initViews() {
        handleIntentArguments()

        binding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

        vm.getMovieDetail(movieId ?: return)
    }

    private fun handleIntentArguments() {
        arguments?.let {
            movieId = it.getString(ARG_KEY_MOVIE_ID) ?: return@let
        } ?: return
    }

    override fun initObservers() {
        vm.movieDetail.observe(viewLifecycleOwner, {
            when (it) {
                is Results.Loading -> {
                    setLoading(true)
                }
                is Results.Success -> {
                    setLoading(false)
                    Timber.tag("RESULT").d("RESULT: ${it.data}")
                    displayMovieDetail(it.data)
                }
                is Results.Error -> {
                    setLoading(false)
                    Timber.tag("RESULT").d("ERROR: ${it.errorMessage.toString()}")
                    showToast(it.errorMessage.toString())
                }
                else -> {
                    setLoading(false)
                }
            }
        })
    }

    private fun displayMovieDetail(data: MovieDetail) {
        binding.data = data
        binding.imageBackdrop.loadBackdrop(data.backdropPath)
    }

    private fun setLoading(state: Boolean) {
        binding.isLoading = state
    }
}