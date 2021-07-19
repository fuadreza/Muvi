package io.github.fuadreza.muvi.presentation.detail

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import io.github.fuadreza.core_android.abstraction.BaseFragment
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.core_android.extensions.loadBackdrop
import io.github.fuadreza.muvi.R
import io.github.fuadreza.muvi.databinding.FragmentMovieDetailBinding
import io.github.fuadreza.muvi.domain.entity.MovieDetail
import io.github.fuadreza.muvi.presentation.detail.review.MovieReviewAdapter
import io.github.fuadreza.muvi.utils.EndlessRecyclerOnScrollListener
import timber.log.Timber

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {

    override fun getViewModelClass(): Class<MovieDetailViewModel> = MovieDetailViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.fragment_movie_detail

    companion object {
        const val ARG_KEY_MOVIE_ID = "movieId"
    }

    private var movieId: String? = null

    private lateinit var movieReviewAdapter: MovieReviewAdapter

    override fun initViews() {
        handleIntentArguments()
        displayMovieReviews()

        binding.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }

        vm.getMovieDetail(movieId ?: return)
        vm.getMovieYoutubeTrailer(movieId ?: return)
        vm.getMovieReviews(movieId ?: return, 1)
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
        vm.movieYoutubeTrailer.observe(viewLifecycleOwner, {
            when (it) {
                is Results.Loading -> {

                }
                is Results.Success -> {
                    Timber.tag("RESULT").d("YOUTUBE: ${it.data}")
                    displayYoutubeTrailer(it.data.key)
                }
                is Results.Error -> {
                    Timber.tag("RESULT").d("ERROR: ${it.errorMessage.toString()}")
                    showToast(it.errorMessage.toString())
                }
                else -> {

                }
            }
        })
        vm.movieReviews.observe(viewLifecycleOwner, {
            when (it) {
                is Results.Loading -> {

                }
                is Results.Success -> {
                    Timber.tag("RESULT").d("REVIEWS: ${it.data}")
                    vm.addListMovieReview(it.data)
                }
                is Results.Error -> {
                    Timber.tag("RESULT").d("ERROR: ${it.errorMessage.toString()}")
                    showToast(it.errorMessage.toString())

                }
                else -> {

                }
            }
        })
        vm.listMovieReviews.observe(viewLifecycleOwner, {
            if (it != null) {
                movieReviewAdapter.addItems(it)
            }
        })
    }

    private fun displayMovieDetail(data: MovieDetail) {
        binding.data = data
        binding.imageBackdrop.loadBackdrop(data.backdropPath)
    }

    private fun displayYoutubeTrailer(key: String) {
        binding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.cueVideo(key, 0F)
            }
        })
    }

    private fun displayMovieReviews() {
        movieReviewAdapter = MovieReviewAdapter {

        }

        val linearLayoutManager = LinearLayoutManager(context)

        binding.rvReviews.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = movieReviewAdapter
            this.addOnScrollListener(object :
                EndlessRecyclerOnScrollListener(linearLayoutManager, 1) {
                override fun onLoadMore(current_page: Int) {
                    vm.getMovieReviews(movieId = movieId ?: return, current_page)
                }
            })
        }
    }

    private fun setLoading(state: Boolean) {
        binding.isLoading = state
    }
}