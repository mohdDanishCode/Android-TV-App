package com.stage.app.presentation

sealed class Screen(val route: String, val titleResId: Int? = null, val selectedIcon: Int? = null, val unselectedIcon: Int? = null) {
    data object HomeScreenMovie : Screen("home_screen_movie")
    data object MovieDetailScreen : Screen("detail/{movieId}")
    data object PlayerScreen : Screen("player/{movieId}")
    data object SplashScreen : Screen("splash_screen")
}
