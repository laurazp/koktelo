package com.luridevlabs.koktelo.domain.usecase

import com.luridevlabs.koktelo.domain.CocktailsRepository
import com.luridevlabs.koktelo.model.Drink

class GetCocktailsUseCase (
    private val cocktailsRepository: CocktailsRepository
) {

    suspend fun execute() : List<Drink> {
        return cocktailsRepository.getCocktails()
    }
}