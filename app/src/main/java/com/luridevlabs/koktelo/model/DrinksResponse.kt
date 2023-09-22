package com.luridevlabs.koktelo.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

data class DrinksResponse (
    val drinks: List<Drink>
)

@Keep
data class Drink (
    @SerializedName("strDrink") val drinkName: String,
    @SerializedName("strDrinkThumb") val drinkImageUrl: String,
    @SerializedName("idDrink") val drinkId: String
)
