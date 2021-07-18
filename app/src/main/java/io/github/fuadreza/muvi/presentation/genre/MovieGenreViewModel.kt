package io.github.fuadreza.muvi.presentation.genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.fuadreza.core_android.abstraction.UseCase
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.MovieGenre
import io.github.fuadreza.muvi.domain.usecase.GetMovieGenresUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieGenreViewModel @Inject constructor(private val getMovieGenresUseCase: GetMovieGenresUseCase): ViewModel() {

    private val _movieGenres = MutableLiveData<Results<List<MovieGenre>>>()
    val movieGenres: LiveData<Results<List<MovieGenre>>> = _movieGenres

    fun getMovieGenres(){
        _movieGenres.value = Results.Loading
        viewModelScope.launch {
            _movieGenres.value = getMovieGenresUseCase(UseCase.None)
        }
    }

}