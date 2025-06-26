package com.stage.app.presentation.modules.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.network.core.dto.movieResponse.Movie

enum class MovieCardStyle {
    STANDARD,
    LARGE,
    CONTINUE_WATCHING
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieRow(
    title: String,
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    cardStyle: MovieCardStyle = MovieCardStyle.STANDARD,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(movies) { movie ->
                when (cardStyle) {
                    MovieCardStyle.STANDARD -> {
                        MovieCard(
                            movie = movie,
                            onClick = { onMovieClick(movie) },
                            modifier = Modifier.width(180.dp)
                        )
                    }
                    MovieCardStyle.LARGE -> {
                        LargeMovieCard(
                            movie = movie,
                            onClick = { onMovieClick(movie) },
                            modifier = Modifier.width(280.dp)
                        )
                    }
                    MovieCardStyle.CONTINUE_WATCHING -> {
                        ContinueWatchingCard(
                            movie = movie,
                            onClick = { onMovieClick(movie) },
                            modifier = Modifier.width(320.dp)
                        )
                    }
                }
            }
        }
    }
}