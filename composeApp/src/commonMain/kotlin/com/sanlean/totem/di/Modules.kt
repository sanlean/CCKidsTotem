package com.sanlean.totem.di

import app.cash.sqldelight.db.SqlDriver
import com.sanlean.totem.data.api.ApiRepository
import com.sanlean.totem.data.api.ClientFactory
import com.sanlean.totem.data.database.DatabaseRepository
import com.sanlean.totem.data.database.DatabaseSetup
import com.sanlean.totem.data.database.DriverFactory
import com.sanlean.totem.domain.os.DeviceWrapper
import com.sanlean.totem.domain.usecase.KeyboardTypeUseCase
import com.sanlean.totem.domain.usecase.RegisterStudentUseCase
import com.sanlean.totem.domain.usecase.SearchStudentUseCase
import com.sanlean.totem.presentation.RegisterViewModel
import com.sanlean.totem.presentation.SearchViewModel
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<HttpClient> { ClientFactory.client }
    single<ApiRepository> { ApiRepository(dispatcher = get(), client = get()) }

    single<SqlDriver> { DriverFactory.createDriver() }
    single { DatabaseSetup.createDatabase(driver = get()) }
    single<DatabaseRepository> { DatabaseRepository(dispatcher = get(), database = get()) }

    single { DeviceWrapper }

    single { SearchStudentUseCase(apiRepository = get(), databaseRepository = get()) }
    single { RegisterStudentUseCase(apiRepository = get(), databaseRepository = get()) }
    single { KeyboardTypeUseCase(deviceWrapper = get()) }

    viewModel { SearchViewModel(searchUseCase = get(), keyboardTypeUseCase = get()) }
    viewModel { RegisterViewModel(registerUseCase = get(), keyboardTypeUseCase = get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}
