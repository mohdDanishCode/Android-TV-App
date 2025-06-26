package com.network.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppApi
annotation class RefreshTokenAPI

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppDao

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatchers: AppDispatchers)

enum class AppDispatchers {
    IO,
}
