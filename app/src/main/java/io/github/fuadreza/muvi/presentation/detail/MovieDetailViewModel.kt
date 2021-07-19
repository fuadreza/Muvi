package io.github.fuadreza.muvi.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.MovieDetail
import io.github.fuadreza.muvi.domain.entity.MovieYoutubeTrailer
import io.github.fuadreza.muvi.domain.usecase.GetMovieDetailUseCase
import io.github.fuadreza.muvi.domain.usecase.GetMovieYoutubeTrailerUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getMovieYoutubeTrailerUseCase: GetMovieYoutubeTrailerUseCase
): ViewModel() {

    private val _movieDetail = MutableLiveData<Results<MovieDetail>>()
    val movieDetail: LiveData<Results<MovieDetail>> = _movieDetail

    private val _movieYoutubeTrailer = MutableLiveData<Results<MovieYoutubeTrailer>>()
    val movieYoutubeTrailer: LiveData<Results<MovieYoutubeTrailer>> = _movieYoutubeTrailer

    fun getMovieDetail(movieId: String){
        _movieDetail.value = Results.Loading
        viewModelScope.launch {
            _movieDetail.value = getMovieDetailUseCase(movieId)
        }
    }

    fun getMovieYoutubeTrailer(movieId: String){
        _movieYoutubeTrailer.value = Results.Loading
        viewModelScope.launch {
            _movieYoutubeTrailer.value = getMovieYoutubeTrailerUseCase(movieId)
        }
    }
}