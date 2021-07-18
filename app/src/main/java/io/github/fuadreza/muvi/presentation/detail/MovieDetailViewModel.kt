package io.github.fuadreza.muvi.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.MovieDetail
import io.github.fuadreza.muvi.domain.usecase.GetMovieDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetailUseCase: GetMovieDetailUseCase): ViewModel() {

    private val _movieDetail = MutableLiveData<Results<MovieDetail>>()
    val movieDetail: LiveData<Results<MovieDetail>> = _movieDetail

    fun getMovieDetail(movieId: String){
        _movieDetail.value = Results.Loading
        viewModelScope.launch {
            _movieDetail.value = getMovieDetailUseCase(movieId)
        }
    }
}