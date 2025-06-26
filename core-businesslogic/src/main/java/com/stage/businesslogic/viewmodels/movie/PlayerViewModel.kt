package com.stage.businesslogic.viewmodels.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.coreDatasource.repository.MovieRepository
import com.network.core.dto.movieResponse.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlayerUiState())
    val uiState: StateFlow<PlayerUiState> = _uiState.asStateFlow()

    fun loadMovie(movieId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                repository.getMovieById(movieId).collect { movie ->
                    _uiState.value = _uiState.value.copy(
                        movie = movie,
                        isLoading = false,
                        error = if (movie == null) "Movie not found" else null
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error occurred"
                )
            }
        }
    }

    fun updatePlaybackPosition(position: Long) {
        _uiState.value = _uiState.value.copy(playbackPosition = position)
    }

    fun setControlsVisibility(visible: Boolean) {
        _uiState.value = _uiState.value.copy(controlsVisible = visible)
    }

    fun setIsPlaying(playing: Boolean) {
        _uiState.value = _uiState.value.copy(isPlaying = playing)
    }

    fun setDuration(duration: Long) {
        _uiState.value = _uiState.value.copy(duration = duration)
    }
}

data class PlayerUiState(
    val movie: Movie? = null,
    val playbackPosition: Long = 0L,
    val duration: Long = 0L,
    val controlsVisible: Boolean = true,
    val isPlaying: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)