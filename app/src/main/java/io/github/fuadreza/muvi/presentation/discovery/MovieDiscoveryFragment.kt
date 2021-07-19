package io.github.fuadreza.muvi.presentation.discovery

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.core_android.abstraction.BaseFragment
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.R
import io.github.fuadreza.muvi.databinding.FragmentMovieDiscoveryBinding
import io.github.fuadreza.muvi.presentation.detail.MovieDetailFragment
import io.github.fuadreza.muvi.utils.EndlessRecyclerOnScrollListener
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
    private var page: Int = 1

    override fun initViews() {
        handleIntentArguments()

        displayMoviesDiscovery()

        binding.genre = genreName ?: "Movie"
        vm.getMoviesDiscoveryByGenre(genres = genreId ?: return, page = page)
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
                    vm.addListMovie(it.data)
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
        vm.listMoviesDiscovery.observe(viewLifecycleOwner, {
            if (it != null) {
                movieDiscoveryAdapter.addItems(it)
            }
        })
    }

    private fun displayMoviesDiscovery() {
        movieDiscoveryAdapter = MovieDiscoveryAdapter { itemMovieDiscovery ->
            findNavController().navigate(
                R.id.action_movieDiscoveryFragment_to_movieDetailFragment,
                bundleOf(MovieDetailFragment.ARG_KEY_MOVIE_ID to itemMovieDiscovery.id.toString())
            )
        }

        val linearLayoutManager = LinearLayoutManager(context)

        binding.rvMoviesDiscovery.apply {
            setHasFixedSize(false)
            layoutManager = linearLayoutManager
            adapter = movieDiscoveryAdapter
            this.addOnScrollListener(object : EndlessRecyclerOnScrollListener(linearLayoutManager) {
                override fun onLoadMore(current_page: Int) {
                    vm.getMoviesDiscoveryByGenre(genres = genreId ?: return, current_page)
                }
            })
        }

    }

    private fun setLoading(state: Boolean) {
        binding.isLoading = state
    }
}