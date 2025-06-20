package com.iagoaf.movieexplorer.src.features.movieDetail.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.movieexplorer.src.features.movieDetail.presentation.MovieDetailState
import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel
import com.iagoaf.movieexplorer.src.shared.movie.domain.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    val moviesRepository: IMoviesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<MovieDetailState>(MovieDetailState.Idle)
    val state: StateFlow<MovieDetailState> = _state.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    private val _movie = MutableStateFlow<MovieModel?>(null)
    val movie: StateFlow<MovieModel?> = _movie.asStateFlow()

    init {
        val encodedMovie = savedStateHandle.get<String>("movie")
        encodedMovie?.let {
            val decoded = Json.decodeFromString<MovieModel>(
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString())
            )
            _movie.value = decoded
            verifyIfMovieIsFavorite(decoded.id)
        }
    }

    private fun verifyIfMovieIsFavorite(movieId: Int) {
        viewModelScope.launch {
            _state.value = MovieDetailState.Loading
            val result = moviesRepository.verifyIfMovieIsFavorite(movieId)
            result.onSuccess { isFav, _ ->
                _isFavorite.value = isFav
                _state.value = MovieDetailState.Success
            }
            result.onError {
                _state.value = MovieDetailState.Idle
            }
        }
    }

    fun toggleFavorite() {
        val movie = _movie.value ?: return
        viewModelScope.launch {
            _state.value = MovieDetailState.Loading
            if (_isFavorite.value.not()) {
                val result = moviesRepository.addMovieToFavorites(movie)
                result.onSuccess { _, _ -> _isFavorite.value = true }
                result.onError { _state.value = MovieDetailState.Idle }
            } else {
                val result = moviesRepository.removeMovieFromFavorites(movie.id)
                result.onSuccess { _, _ -> _isFavorite.value = false }
                result.onError { _state.value = MovieDetailState.Idle }
            }
            _state.value = MovieDetailState.Success
        }
    }


}