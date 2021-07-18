package io.github.fuadreza.muvi.presentation.discovery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.fuadreza.core_android.data.vo.Results
import io.github.fuadreza.muvi.domain.entity.ItemMovieDiscovery
import io.github.fuadreza.muvi.domain.usecase.GetMoviesDiscoveryByGenreUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDiscoveryViewModel @Inject constructor(private val getMoviesDiscoveryByGenreUseCase: GetMoviesDiscoveryByGenreUseCase): ViewModel() {

    private val _moviesDiscovery = MutableLiveData<Results<List<ItemMovieDiscovery>>>()
    val moviesDiscovery: LiveData<Results<List<ItemMovieDiscovery>>> = _moviesDiscovery

    fun getMoviesDiscoveryByGenre(genre: String){
        _moviesDiscovery.value = Results.Loading
        viewModelScope.launch {
            _moviesDiscovery.value = getMoviesDiscoveryByGenreUseCase(genre)
        }
    }

}