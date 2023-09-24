package com.luridevlabs.koktelo.domain.usecase

import com.luridevlabs.koktelo.domain.CocktailsRepository
import com.luridevlabs.koktelo.model.Drink

class GetCocktailDetailUseCase (
    private val cocktailsRepository: CocktailsRepository
) {

    suspend fun execute(cocktailId: String) : Drink {
        return cocktailsRepository.getCocktail(cocktailId)
    }
}