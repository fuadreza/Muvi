package io.github.fuadreza.muvi.presentation.genre

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.core_android.abstraction.BaseFragment
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.R
import io.github.fuadreza.muvi.databinding.FragmentMovieGenresBinding
import io.github.fuadreza.muvi.domain.entity.MovieGenre
import io.github.fuadreza.muvi.presentation.discovery.MovieDiscoveryFragment
import timber.log.Timber

@AndroidEntryPoint
class MovieGenreFragment: BaseFragment<FragmentMovieGenresBinding, MovieGenreViewModel>() {
    override fun getViewModelClass(): Class<MovieGenreViewModel> = MovieGenreViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_genres

    private lateinit var movieGenreAdapter: MovieGenreAdapter

    override fun initViews() {
        vm.getMovieGenres()
    }

    private fun displayMovieGenres(data: List<MovieGenre>) {
        movieGenreAdapter = MovieGenreAdapter{ movieGenre ->
            findNavController().navigate(
                R.id.action_movieGenreFragment_to_movieDiscoveryFragment,
                bundleOf(
                    MovieDiscoveryFragment.ARG_KEY_MOVIE_GENRE_ID to movieGenre.id.toString(),
                    MovieDiscoveryFragment.ARG_KEY_MOVIE_GENRE_NAME to movieGenre.name
                )
            )
        }

        binding.rvGenres.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context)
            adapter = movieGenreAdapter
        }

        movieGenreAdapter.addItems(data)
    }

    override fun initObservers() {
        vm.movieGenres.observe(viewLifecycleOwner, {
            when (it){
                is Results.Loading -> {
                    setLoading(true)
                }
                is Results.Success -> {
                    setLoading(false)
                    displayMovieGenres(it.data)
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

    private fun setLoading(state: Boolean) {
        binding.isLoading = state
    }

}