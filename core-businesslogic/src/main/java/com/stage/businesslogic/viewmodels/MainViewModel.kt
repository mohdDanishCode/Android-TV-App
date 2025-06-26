package com.stage.businesslogic.viewmodels

import android.app.Application
import android.content.Context
import com.network.core.di.AppDispatchers
import com.network.core.di.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val appApplication: Application,
    @ApplicationContext val context: Context,
    @Dispatcher(AppDispatchers.IO) private val dispatchers: CoroutineDispatcher,

) : BaseViewModel(appApplication) {
    companion object {
        const val TAG = "MainViewModel"
    }


}
