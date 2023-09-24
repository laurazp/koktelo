package com.luridevlabs.koktelo.domain

import com.luridevlabs.koktelo.model.Drink

interface CocktailsRepository {

    suspend fun getCocktails(): List<Drink>

    suspend fun getCocktail(cocktailId: String): Drink
}