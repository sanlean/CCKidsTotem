package com.sanlean.totem.di

import com.sanlean.totem.data.ClassClient
import com.sanlean.totem.data.ClassRepository
import com.sanlean.totem.domain.usecase.SearchStudentUseCase
import com.sanlean.totem.presentation.SearchViewModel
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<HttpClient> { ClassClient.client }
    single { ClassRepository(dispatcher = get(), client = get()) }
    single { SearchStudentUseCase(repository = get()) }
    viewModel { SearchViewModel(searchUseCase = get()) }
}

fun initKoin(){
    startKoin {
        modules(appModule)
    }
}