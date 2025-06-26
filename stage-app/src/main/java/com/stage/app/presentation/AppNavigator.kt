package com.stage.app.presentation
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import com.danish.common.base.activity.BaseActivity

class AppNavigator(
    private val navController: NavController,
    private val activity: BaseActivity,
) {



    private var _previousRoute: String? = null
    val previousRoute: String?
        get() = _previousRoute

    private var _currentRoute: String? = null
    val currentRoute: String?
        get() = _currentRoute

    fun updateRoutes(newRoute: String?) {
        _previousRoute = _currentRoute
        _currentRoute = newRoute
    }



    fun getNavController() = navController


    fun navigateToScreen(
        screen: Screen
    ) {
        navController.navigate(screen.route)
    }

    fun navigateByPopUpToScreen(
        screen: Screen,
        isSaveState:Boolean=false
    ) {
        navController.navigate(screen.route) {
            popUpTo(0) {
                saveState = isSaveState
                inclusive = true
            }
            restoreState = isSaveState
            launchSingleTop = isSaveState

        }
    }

    fun popBackStack() {
        if(!navController.popBackStack()){
            activity.finish()
        }
    }

    fun popBackStackTillSomeDestination(screen: Screen,inclusive:Boolean) {
        if(!navController.popBackStack(screen.route,inclusive)){
            activity.finish()
        }
    }



}

val LocalAppNavigator: ProvidableCompositionLocal<AppNavigator> =
    compositionLocalOf { error("No AppNavigator found!") }
