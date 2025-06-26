package com.stage.app.presentation.modules.player

import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.network.core.dto.movieResponse.Movie
import com.stage.app.R
import com.stage.businesslogic.viewmodels.movie.PlayerViewModel
import kotlinx.coroutines.delay

enum class PlayerFocus {
    PLAYER_VIEW, PLAY_PAUSE, SKIP_BACKWARD, SKIP_FORWARD, PROGRESS_BAR, EXIT_BUTTON
}

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(
    movieId: String,
    onBackClick: () -> Unit,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var controlsVisible by remember { mutableStateOf(true) }
    var currentPosition by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }
    var isPlaying by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    var currentFocus by remember { mutableStateOf(PlayerFocus.PLAYER_VIEW) }

    val uiState by viewModel.uiState.collectAsState()

    // Focus requesters for different UI elements
    val playerViewFocusRequester = remember { FocusRequester() }
    val playPauseFocusRequester = remember { FocusRequester() }
    val skipBackwardFocusRequester = remember { FocusRequester() }
    val skipForwardFocusRequester = remember { FocusRequester() }
    val progressBarFocusRequester = remember { FocusRequester() }
    val exitButtonFocusRequester = remember { FocusRequester() }


    val exoPlayer = remember {
        ExoPlayer.Builder(context).build()
    }


    LaunchedEffect(movieId) {
        viewModel.loadMovie(movieId)
    }


    LaunchedEffect(uiState.movie) {
        uiState.movie?.let { movie ->
            try {
                val mediaItem = MediaItem.fromUri(movie.videoUrl)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.playWhenReady = true
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
                // Handle error
            }
        }
    }

    // Player listener
    DisposableEffect(exoPlayer) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                isLoading = playbackState == Player.STATE_BUFFERING
                if (playbackState == Player.STATE_READY) {
                    duration = exoPlayer.duration
                }
            }

            override fun onIsPlayingChanged(playing: Boolean) {
                isPlaying = playing
            }
        }

        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    // Update position periodically
    LaunchedEffect(exoPlayer, isPlaying) {
        while (isPlaying) {
            currentPosition = exoPlayer.currentPosition
            delay(1000)
        }
    }

    // Auto-hide controls
    LaunchedEffect(controlsVisible, isPlaying, currentFocus) {
        if (controlsVisible && isPlaying && currentFocus == PlayerFocus.PLAYER_VIEW) {
            delay(5000) // Longer delay for TV
            controlsVisible = false
        }
    }

    // Focus management
    LaunchedEffect(controlsVisible, currentFocus) {
        if (controlsVisible) {
            when (currentFocus) {
                PlayerFocus.PLAY_PAUSE -> playPauseFocusRequester.requestFocus()
                PlayerFocus.SKIP_BACKWARD -> skipBackwardFocusRequester.requestFocus()
                PlayerFocus.SKIP_FORWARD -> skipForwardFocusRequester.requestFocus()
                PlayerFocus.PROGRESS_BAR -> progressBarFocusRequester.requestFocus()
                PlayerFocus.EXIT_BUTTON -> exitButtonFocusRequester.requestFocus()
                else -> playerViewFocusRequester.requestFocus()
            }
        } else {
            playerViewFocusRequester.requestFocus()
        }
    }

    // Key event handling
    fun handleKeyEvent(keyEvent: KeyEvent): Boolean {
        if (keyEvent.action != KeyEvent.ACTION_DOWN) return false

        return when (keyEvent.keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                when (currentFocus) {
                    PlayerFocus.PLAYER_VIEW -> {
                        controlsVisible = !controlsVisible
                        if (controlsVisible) {
                            currentFocus = PlayerFocus.PLAY_PAUSE
                        }
                        true
                    }
                    PlayerFocus.PLAY_PAUSE -> {
                        if (isPlaying) exoPlayer.pause() else exoPlayer.play()
                        true
                    }
                    PlayerFocus.SKIP_BACKWARD -> {
                        val newPosition = (currentPosition - 10000).coerceAtLeast(0)
                        exoPlayer.seekTo(newPosition)
                        true
                    }
                    PlayerFocus.SKIP_FORWARD -> {
                        val newPosition = (currentPosition + 10000).coerceAtMost(duration)
                        exoPlayer.seekTo(newPosition)
                        true
                    }
                    PlayerFocus.EXIT_BUTTON -> {
                        onBackClick()
                        true
                    }
                    else -> false
                }
            }
            KeyEvent.KEYCODE_DPAD_UP -> {
                if (controlsVisible) {
                    currentFocus = when (currentFocus) {
                        PlayerFocus.PLAY_PAUSE, PlayerFocus.SKIP_BACKWARD, PlayerFocus.SKIP_FORWARD -> PlayerFocus.PROGRESS_BAR
                        PlayerFocus.PROGRESS_BAR -> PlayerFocus.EXIT_BUTTON
                        else -> currentFocus
                    }
                    true
                } else false
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                if (controlsVisible) {
                    currentFocus = when (currentFocus) {
                        PlayerFocus.EXIT_BUTTON -> PlayerFocus.PROGRESS_BAR
                        PlayerFocus.PROGRESS_BAR -> PlayerFocus.PLAY_PAUSE
                        else -> currentFocus
                    }
                    true
                } else {
                    controlsVisible = true
                    currentFocus = PlayerFocus.PLAY_PAUSE
                    true
                }
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if (controlsVisible) {
                    currentFocus = when (currentFocus) {
                        PlayerFocus.PLAY_PAUSE -> PlayerFocus.SKIP_BACKWARD
                        PlayerFocus.SKIP_FORWARD -> PlayerFocus.PLAY_PAUSE
                        PlayerFocus.PROGRESS_BAR -> {
                            // Fine seek backward when on progress bar (5 seconds)
                            val seekAmount = (duration * 0.01f).toLong().coerceAtLeast(5000L) // 1% or 5 seconds minimum
                            val newPosition = (currentPosition - seekAmount).coerceAtLeast(0)
                            exoPlayer.seekTo(newPosition)
                            currentFocus
                        }
                        else -> currentFocus
                    }
                    true
                } else {
                    // Quick seek backward
                    val newPosition = (currentPosition - 10000).coerceAtLeast(0)
                    exoPlayer.seekTo(newPosition)
                    true
                }
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                if (controlsVisible) {
                    currentFocus = when (currentFocus) {
                        PlayerFocus.SKIP_BACKWARD -> PlayerFocus.PLAY_PAUSE
                        PlayerFocus.PLAY_PAUSE -> PlayerFocus.SKIP_FORWARD
                        PlayerFocus.PROGRESS_BAR -> {
                            // Fine seek forward when on progress bar (5 seconds)
                            val seekAmount = (duration * 0.01f).toLong().coerceAtLeast(5000L) // 1% or 5 seconds minimum
                            val newPosition = (currentPosition + seekAmount).coerceAtMost(duration)
                            exoPlayer.seekTo(newPosition)
                            currentFocus
                        }
                        else -> currentFocus
                    }
                    true
                } else {
                    // Quick seek forward
                    val newPosition = (currentPosition + 10000).coerceAtMost(duration)
                    exoPlayer.seekTo(newPosition)
                    true
                }
            }
            KeyEvent.KEYCODE_BACK -> {
                if (controlsVisible) {
                    controlsVisible = false
                    currentFocus = PlayerFocus.PLAYER_VIEW
                    true
                } else {
                    onBackClick()
                    true
                }
            }
            KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, KeyEvent.KEYCODE_SPACE -> {
                if (isPlaying) exoPlayer.pause() else exoPlayer.play()
                true
            }
            KeyEvent.KEYCODE_MEDIA_FAST_FORWARD -> {
                val newPosition = (currentPosition + 30000).coerceAtMost(duration)
                exoPlayer.seekTo(newPosition)
                true
            }
            KeyEvent.KEYCODE_MEDIA_REWIND -> {
                val newPosition = (currentPosition - 30000).coerceAtLeast(0)
                exoPlayer.seekTo(newPosition)
                true
            }
            else -> false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .onKeyEvent { keyEvent ->
                handleKeyEvent(keyEvent.nativeKeyEvent)
            }
            .focusRequester(playerViewFocusRequester)
            .focusable()
    ) {
        // Show loading indicator while buffering
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }

        // Player View
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    useController = false
                    hideController()
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Custom controls overlay
        if (controlsVisible) {
            PlayerControls(
                movie = uiState.movie,
                isPlaying = isPlaying,
                currentPosition = currentPosition,
                duration = duration,
                currentFocus = currentFocus,
                onPlayPause = {
                    if (isPlaying) exoPlayer.pause() else exoPlayer.play()
                },
                onSeek = { position ->
                    exoPlayer.seekTo(position)
                },
                onSkipForward = {
                    val newPosition = (currentPosition + 10000).coerceAtMost(duration)
                    exoPlayer.seekTo(newPosition)
                },
                onSkipBackward = {
                    val newPosition = (currentPosition - 10000).coerceAtLeast(0)
                    exoPlayer.seekTo(newPosition)
                },
                onBackClick = onBackClick,
                playPauseFocusRequester = playPauseFocusRequester,
                skipBackwardFocusRequester = skipBackwardFocusRequester,
                skipForwardFocusRequester = skipForwardFocusRequester,
                progressBarFocusRequester = progressBarFocusRequester,
                exitButtonFocusRequester = exitButtonFocusRequester,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
private fun PlayerControls(
    movie: Movie?,
    isPlaying: Boolean,
    currentPosition: Long,
    duration: Long,
    currentFocus: PlayerFocus,
    onPlayPause: () -> Unit,
    onSeek: (Long) -> Unit,
    onSkipForward: () -> Unit,
    onSkipBackward: () -> Unit,
    onBackClick: () -> Unit,
    playPauseFocusRequester: FocusRequester,
    skipBackwardFocusRequester: FocusRequester,
    skipForwardFocusRequester: FocusRequester,
    progressBarFocusRequester: FocusRequester,
    exitButtonFocusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Color.Black.copy(alpha = 0.8f),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .padding(24.dp)
    ) {
        // Movie info
        movie?.let { movieInfo ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = movieInfo.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${movieInfo.genre} • ${movieInfo.year}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }

                Button(
                    onClick = onBackClick,
                    colors = ButtonDefaults.colors(
                        containerColor = if (currentFocus == PlayerFocus.EXIT_BUTTON)
                            MaterialTheme.colorScheme.primary
                        else Color.White.copy(alpha = 0.2f),
                        focusedContainerColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .focusRequester(exitButtonFocusRequester)
                        .onFocusChanged { }
                ) {
                    Text(stringResource(R.string.exit), color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Progress bar - Custom seekbar with fine control
        if (duration > 0) {
            Column {
                // Progress bar container with focus indicator
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                        .background(
                            if (currentFocus == PlayerFocus.PROGRESS_BAR)
                                Color.White.copy(alpha = 0.5f)
                            else Color.White.copy(alpha = 0.3f),
                            RoundedCornerShape(6.dp)
                        )
                        .focusRequester(progressBarFocusRequester)
                        .focusable()
                        .onFocusChanged { }
                ) {
                    // Progress fill
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(currentPosition.toFloat() / duration.toFloat())
                            .background(
                                if (currentFocus == PlayerFocus.PROGRESS_BAR)
                                    MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                                RoundedCornerShape(6.dp)
                            )
                    )

                    // Seek thumb indicator when focused
                    if (currentFocus == PlayerFocus.PROGRESS_BAR) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(4.dp)
                                .background(
                                    Color.White,
                                    RoundedCornerShape(2.dp)
                                )
                                .align(Alignment.CenterStart)
                                .offset(
                                    x = ((currentPosition.toFloat() / duration.toFloat()) *
                                            (LocalContext.current.resources.displayMetrics.widthPixels - 96)).dp /
                                            LocalContext.current.resources.displayMetrics.density
                                )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = formatTime(currentPosition),
                        style = MaterialTheme.typography.bodySmall,
                        color = if (currentFocus == PlayerFocus.PROGRESS_BAR)
                            Color.White else Color.White.copy(alpha = 0.7f),
                        fontWeight = if (currentFocus == PlayerFocus.PROGRESS_BAR)
                            FontWeight.Bold else FontWeight.Normal
                    )
                    if (currentFocus == PlayerFocus.PROGRESS_BAR) {
                        Text(
                            text = stringResource(R.string.to_seek),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = formatTime(duration),
                        style = MaterialTheme.typography.bodySmall,
                        color = if (currentFocus == PlayerFocus.PROGRESS_BAR)
                            Color.White else Color.White.copy(alpha = 0.7f),
                        fontWeight = if (currentFocus == PlayerFocus.PROGRESS_BAR)
                            FontWeight.Bold else FontWeight.Normal
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Control buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onSkipBackward,
                colors = ButtonDefaults.colors(
                    containerColor = if (currentFocus == PlayerFocus.SKIP_BACKWARD)
                        MaterialTheme.colorScheme.primary
                    else Color.White.copy(alpha = 0.2f),
                    focusedContainerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .focusRequester(skipBackwardFocusRequester)
                    .onFocusChanged { }
            ) {
                Text(stringResource(R.string._10s), color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = onPlayPause,
                colors = ButtonDefaults.colors(
                    containerColor = if (currentFocus == PlayerFocus.PLAY_PAUSE)
                        MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                    focusedContainerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .focusRequester(playPauseFocusRequester)
                    .onFocusChanged { }
                    .size(56.dp)
            ) {
                Text(
                    text = if (isPlaying) "⏸" else "▶",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = onSkipForward,
                colors = ButtonDefaults.colors(
                    containerColor = if (currentFocus == PlayerFocus.SKIP_FORWARD)
                        MaterialTheme.colorScheme.primary
                    else Color.White.copy(alpha = 0.2f),
                    focusedContainerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .focusRequester(skipForwardFocusRequester)
                    .onFocusChanged { }
            ) {
                Text(stringResource(R.string.next_10s), color = Color.White)
            }
        }

        // Help text for TV navigation
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = when (currentFocus) {
                PlayerFocus.PROGRESS_BAR -> stringResource(R.string.seek_navigate_ok_select_back_hide)
                else -> stringResource(R.string.navigate_ok_select_back_hide_controls)
            },
            style = MaterialTheme.typography.bodySmall,
            color = Color.White.copy(alpha = 0.6f),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

private fun formatTime(timeMs: Long): String {
    if (timeMs <= 0) return "0:00"
    val totalSeconds = timeMs / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%d:%02d", minutes, seconds)
}