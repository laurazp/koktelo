package com.luridevlabs.koktelo.data.cocktail.remote

import com.luridevlabs.koktelo.data.remote.KokteloService
import com.luridevlabs.koktelo.model.Drink

class CocktailsRemoteImpl(
    private val kokteloService: KokteloService
) {

    suspend fun getCocktails(): List<Drink> {
        return kokteloService.getCocktails().drinks
    }

    suspend fun getCocktail(cocktailId: String): Drink {
        return kokteloService.getCocktail(cocktailId)
    }
}