package com.luridevlabs.koktelo.data.cocktail

import com.luridevlabs.koktelo.data.cocktail.local.CocktailsLocalImpl
import com.luridevlabs.koktelo.data.cocktail.remote.CocktailsRemoteImpl
import com.luridevlabs.koktelo.domain.CocktailsRepository
import com.luridevlabs.koktelo.model.Drink

class CocktailsDataImpl(
    private val cocktailsRemoteImpl: CocktailsRemoteImpl
    //private val cocktailsLocalImpl: CocktailsLocalImpl
) : CocktailsRepository {

    override suspend fun getCocktails(): List<Drink> {
        return cocktailsRemoteImpl.getCocktails()
    }

    override suspend fun getCocktail(cocktailId: String): Drink {
        return cocktailsRemoteImpl.getCocktail(cocktailId)
    }

    /*override fun saveCocktails(cocktails: List<Cocktail>) {
        cocktailsLocalImpl.saveCocktails(cocktails)
    }*/

}