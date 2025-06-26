package com.stage.app.presentation.modules.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import com.network.core.dto.movieResponse.Movie
import com.network.core.dto.movieResponse.MovieRail
import com.stage.app.R
import com.stage.app.presentation.common.MoviePosterOrVideoPreview
import com.stage.app.presentation.modules.home.components.CustomHorizontalPagerIndicator
import com.stage.app.presentation.modules.home.components.MovieCardStyle
import com.stage.app.presentation.modules.home.components.MovieRow
import com.stage.businesslogic.viewmodels.movie.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onMovieClick: (Movie) -> Unit,
    onPlayClick: (Movie) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()


    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    uiState.error?.let { error ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Error: $error",
                color = MaterialTheme.colorScheme.error
            )
        }
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        uiState.movies.forEach { rail ->
            when (rail.railType) {
                MovieRail.RaleType.FEATURED_MOVIE -> {
                    if (rail.movies.isNotEmpty()) {
                        item {
                            FeaturedMoviePager(
                                movies = rail.movies,
                                onPlayClick = { onPlayClick(rail.movies.first()) },
                                onDetailsClick = { onMovieClick(rail.movies.first()) },
                            )
                        }
                    }
                }

                MovieRail.RaleType.CONTINUE_WATCHING -> {
                    item {
                        MovieRow(
                            title = rail.title,
                            movies = rail.movies,
                            onMovieClick = onMovieClick,
                            cardStyle = MovieCardStyle.CONTINUE_WATCHING
                        )
                    }
                }

                MovieRail.RaleType.STANDARD -> {
                    item {
                        MovieRow(
                            title = rail.title,
                            movies = rail.movies,
                            onMovieClick = onMovieClick,
                            cardStyle = MovieCardStyle.STANDARD
                        )
                    }
                }

                MovieRail.RaleType.LARGE -> {
                    item {
                        MovieRow(
                            title = rail.title,
                            movies = rail.movies,
                            onMovieClick = onMovieClick,
                            cardStyle = MovieCardStyle.LARGE
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
        }
    }



}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeaturedMoviePager(
    movies: List<Movie>,
    onPlayClick: (Movie) -> Unit,
    onDetailsClick: (Movie) -> Unit
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val currentMovieIndex = listState.firstVisibleItemIndex.coerceIn(0, movies.lastIndex)
    val currentMovie = movies.getOrNull(currentMovieIndex)

    // Auto-scroll logic
    LaunchedEffect(listState) {
        while (true) {
            delay(10_000)
            val nextIndex = (listState.firstVisibleItemIndex + 1) % movies.size
            scope.launch {
                listState.animateScrollToItem(nextIndex)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxSize(),
            userScrollEnabled = false
        ) {
            itemsIndexed(movies) { index, movie ->


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .width(LocalConfiguration.current.screenWidthDp.dp)
                        .background(Color.Black)
                ) {
                    MoviePosterOrVideoPreview(
                        movie = movie,
                        playVideo = listState.firstVisibleItemIndex == index,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        // Move section outside the LazyRow
        currentMovie?.let {
            FeaturedMovieSection(
                movie = it,
                onPlayClick = { onPlayClick(it) },
                onDetailsClick = { onDetailsClick(it) },
                modifier = Modifier
                    .matchParentSize()
                    .zIndex(1f) // Make sure it overlays
            )
        }

        // Optional indicator
        CustomHorizontalPagerIndicator(
            pageCount = movies.size,
            currentPage = currentMovieIndex,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}






@Composable
private fun FeaturedMovieSection(
    movie: Movie,
    onPlayClick: () -> Unit,
    onDetailsClick: () -> Unit,
    modifier: Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Box(
        modifier = modifier
            .fillMaxWidth()

    ) {

        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.8f),
                            Color.Transparent
                        ),
                        endX = 800f
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(48.dp)
                .width(500.dp)
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${movie.genre} • ${movie.duration} • ${movie.year}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = movie.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onPlayClick,
                    modifier = Modifier,
                    colors = ButtonDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(stringResource(R.string.play))
                }

                Button(
                    onClick = onDetailsClick,
                    colors = ButtonDefaults.colors(
                        containerColor = Color.White.copy(alpha = 0.2f),
                    ),
                    interactionSource = interactionSource
                ) {
                    Text(stringResource(R.string.more_info),  color = if (isFocused) Color.Black else Color.White)
                }
            }
        }
    }
}