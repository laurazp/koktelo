package com.luridevlabs.koktelo.di

import com.luridevlabs.koktelo.data.cocktail.CocktailsDataImpl
import com.luridevlabs.koktelo.data.cocktail.local.CocktailsLocalImpl
import com.luridevlabs.koktelo.data.cocktail.remote.CocktailsRemoteImpl
import com.luridevlabs.koktelo.data.remote.ApiClient
import com.luridevlabs.koktelo.data.remote.KokteloService
import com.luridevlabs.koktelo.domain.CocktailsRepository
import com.luridevlabs.koktelo.domain.usecase.GetCocktailDetailUseCase
import com.luridevlabs.koktelo.domain.usecase.GetCocktailsUseCase
import com.luridevlabs.koktelo.presentation.viewmodel.CocktailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val baseModule = module {
    single<KokteloService> { ApiClient.retrofit.create(KokteloService::class.java) }
}

val charactersModule = module {
    factory { CocktailsRemoteImpl(get()) }
    //factory { CocktailsLocalImpl(get()) }
    factory<CocktailsRepository> { CocktailsDataImpl(get()) }

    factory { GetCocktailsUseCase(get()) }
    factory { GetCocktailDetailUseCase(get()) }

    viewModel { CocktailsViewModel(get(), get()) }
}