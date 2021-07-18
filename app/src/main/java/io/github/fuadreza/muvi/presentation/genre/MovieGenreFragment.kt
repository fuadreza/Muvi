package io.github.fuadreza.muvi.presentation.genre

import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.core_android.abstraction.BaseFragment
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.R
import io.github.fuadreza.muvi.databinding.FragmentMovieGenresBinding
import io.github.fuadreza.muvi.domain.entity.MovieGenre
import timber.log.Timber

@AndroidEntryPoint
class MovieGenreFragment: BaseFragment<FragmentMovieGenresBinding, MovieGenreViewModel>() {
    override fun getViewModelClass(): Class<MovieGenreViewModel> = MovieGenreViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_genres

    private lateinit var movieGenreAdapter: MovieGenreAdapter

    override fun initViews() {
        vm.getMovieGenres()
    }

    private fun setupGenres(data: List<MovieGenre>) {
        movieGenreAdapter = MovieGenreAdapter{ movieGenre ->
            showToast("Genre: $movieGenre")
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
                    setupGenres(it.data)
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