package com.stage.app.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.MaterialTheme
import com.danish.common.base.activity.BaseActivity
import com.stage.app.presentation.modules.MainGraph
import com.stage.app.presentation.ui.theme.OmnifulAppTheme
import com.stage.businesslogic.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val mainViewModel by viewModels<MainViewModel>()


    private lateinit var appNavigator: AppNavigator




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showContentNow()

    }




    @SuppressLint("SourceLockedOrientationActivity")
    private fun showContentNow() {

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                OmnifulAppTheme {
                    val navController = rememberNavController()
                    appNavigator = AppNavigator(navController,this@MainActivity)

                    CompositionLocalProvider(LocalAppNavigator provides appNavigator) {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            bottomBar = {

                            },
                        ) { paddingValues ->

                            Box {
                                MainGraph(navController = navController,paddingValues = paddingValues,intent, mainViewModel )



                            }
                        }
                    }
                }
            }

        }
    }

}



