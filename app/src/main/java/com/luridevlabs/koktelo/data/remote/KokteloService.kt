package com.luridevlabs.koktelo.data.remote

import com.luridevlabs.koktelo.model.Drink
import com.luridevlabs.koktelo.model.DrinksResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface KokteloService {

    @GET("filter.php?c=Cocktail")
    suspend fun getCocktails(): DrinksResponse

    @GET("lookup.php?i=drinkId")
    suspend fun getCocktail(@Path("drinkId") drinkId: String): Drink
}