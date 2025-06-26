package com.stage.app.presentation.modules

import android.content.Intent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.stage.app.presentation.LocalAppNavigator
import com.stage.app.presentation.Screen
import com.stage.app.presentation.modules.splash.SplashScreen
import com.stage.businesslogic.viewmodels.MainViewModel
import com.stage.app.presentation.modules.detail.DetailScreen
import com.stage.app.presentation.modules.player.PlayerScreen

@Composable
fun MainGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    intent: Intent,
    mainViewModel: MainViewModel
) {
    val context = LocalContext.current
    val appNavigator = LocalAppNavigator.current

    val currentBackStackEntry by navController.currentBackStackEntryAsState()



    LaunchedEffect(currentBackStackEntry) {
        appNavigator.updateRoutes(currentBackStackEntry?.destination?.route)
    }




    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {

        animatedComposable(
            route = Screen.HomeScreenMovie.route,
        ) {
            com.stage.app.presentation.modules.home.HomeScreen(
                onMovieClick = { movie ->
                    navController.navigate("detail/${movie.id}")
                },
                onPlayClick = { movie ->
                    navController.navigate("player/${movie.id}")
                })
        }


        animatedComposable(
            route = Screen.MovieDetailScreen.route,
        ) { backStackEntry->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            DetailScreen(
                movieId = movieId,
                onPlayClick = {
                    navController.navigate("player/$movieId")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        animatedComposable(
            route = Screen.PlayerScreen.route,
        ) {backStackEntry->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            PlayerScreen(
                movieId = movieId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }



        animatedComposable(
            route = Screen.SplashScreen.route,
        ) {
            SplashScreen {
                appNavigator.navigateByPopUpToScreen(Screen.HomeScreenMovie)
            }
        }



    }
}


fun NavGraphBuilder.animatedComposable(
    route: String,
    deepLinks: List<NavDeepLink> = mutableListOf<NavDeepLink>(),
    arguments: List<NamedNavArgument> = mutableListOf(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    this.composable(
        route = route,
        arguments = arguments,
        deepLinks = deepLinks,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(700)
            ) + fadeIn(animationSpec = tween(700))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(700)
            ) + fadeOut(animationSpec = tween(700))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth }, animationSpec = tween(700)
            ) + fadeIn(animationSpec = tween(700))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(700)
            ) + fadeOut(animationSpec = tween(700))
        },
    ) { backStack->

        content(backStack)
    }

}

