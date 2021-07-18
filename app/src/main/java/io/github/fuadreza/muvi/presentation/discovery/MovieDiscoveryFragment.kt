package io.github.fuadreza.muvi.presentation.discovery

import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.core_android.abstraction.BaseFragment
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.R
import io.github.fuadreza.muvi.databinding.FragmentMovieDiscoveryBinding
import io.github.fuadreza.muvi.domain.entity.ItemMovieDiscovery
import timber.log.Timber

@AndroidEntryPoint
class MovieDiscoveryFragment :
    BaseFragment<FragmentMovieDiscoveryBinding, MovieDiscoveryViewModel>() {

    override fun getViewModelClass(): Class<MovieDiscoveryViewModel> =
        MovieDiscoveryViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_discovery

    companion object {
        const val ARG_KEY_MOVIE_GENRE_ID = "movieGenreId"
        const val ARG_KEY_MOVIE_GENRE_NAME = "movieGenreName"
    }

    private lateinit var movieDiscoveryAdapter: MovieDiscoveryAdapter

    private var genreId: String? = null
    private var genreName: String? = null

    override fun initViews() {
        handleIntentArguments()

        binding.genre = genreName ?: "Movie"
        vm.getMoviesDiscoveryByGenre(genreId ?: return)
    }

    private fun handleIntentArguments() {
        arguments?.let {
            genreId = it.getString(ARG_KEY_MOVIE_GENRE_ID) ?: return@let
            genreName = it.getString(ARG_KEY_MOVIE_GENRE_NAME) ?: return@let
        } ?: return
    }

    override fun initObservers() {
        vm.moviesDiscovery.observe(viewLifecycleOwner, {
            when (it) {
                is Results.Loading -> {
                    setLoading(true)
                }
                is Results.Success -> {
                    setLoading(false)
                    Timber.tag("RESULT").d("RESULT: ${it.data}")
                    displayMoviesDiscovery(it.data)
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

    private fun displayMoviesDiscovery(data: List<ItemMovieDiscovery>) {
        movieDiscoveryAdapter = MovieDiscoveryAdapter { itemMovieDiscovery ->
            showToast("SELECTED: ${itemMovieDiscovery.title}")
        }

        binding.rvMoviesDiscovery.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = movieDiscoveryAdapter
        }

        movieDiscoveryAdapter.addItems(data)
    }

    private fun setLoading(state: Boolean) {
        binding.isLoading = state
    }
}