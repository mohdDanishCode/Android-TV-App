package com.stage.app.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.network.core.dto.movieResponse.Movie
import kotlinx.coroutines.delay

@Composable
fun MoviePosterOrVideoPreview(
    movie: Movie,
    playVideo: Boolean,
    modifier: Modifier
) {
    var shouldPlayVideo by rememberSaveable(playVideo, movie.id) {
        mutableStateOf(false)
    }

    LaunchedEffect(playVideo, movie.id) {
        delay(1000)
        shouldPlayVideo = playVideo
    }

    Box(modifier = modifier) {
        VideoPlayer(
            videoUrl = movie.videoUrl,
            modifier = modifier
        )
        if (!shouldPlayVideo) {
            ImageGeneric(
                modifier = modifier,
                src = movie.posterUrl,
                imageScale = ContentScale.Crop
            )
        }

    }
}