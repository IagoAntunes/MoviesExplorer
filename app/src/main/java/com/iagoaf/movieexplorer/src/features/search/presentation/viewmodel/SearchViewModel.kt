package com.iagoaf.movieexplorer.src.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.movieexplorer.src.features.search.presentation.state.SearchState
import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel
import com.iagoaf.movieexplorer.src.shared.movie.domain.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val movieRepository: IMoviesRepository
) : ViewModel() {
    private val _state = MutableStateFlow<SearchState>(SearchState.Idle)
    val state: StateFlow<SearchState> = _state.asStateFlow()

    private val _movies = mutableListOf<MovieModel>()
    private var currentPage = 1
    private var isLoading = false
    private var hasMore = true

    fun searchMovie(query: String, reset: Boolean) {
        if (!reset && (isLoading || !hasMore)) return

        viewModelScope.launch {
            isLoading = true

            if (reset) {
                currentPage = 1
                _movies.clear()
                hasMore = true
                _state.value = SearchState.Loading
            }

            val result = movieRepository.searchMovies(query, currentPage)
            result.onSuccess { movies, totalPages ->
                if (currentPage == totalPages) hasMore = false
                _movies.addAll(movies)
                _state.value = SearchState.Success(_movies.toList())
                currentPage++
            }.onError { error ->
                _state.value = SearchState.Error(error)
            }

            isLoading = false
        }
    }


}