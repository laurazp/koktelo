package com.luridevlabs.koktelo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luridevlabs.koktelo.domain.usecase.GetCocktailDetailUseCase
import com.luridevlabs.koktelo.domain.usecase.GetCocktailsUseCase
import com.luridevlabs.koktelo.model.Drink
import com.luridevlabs.koktelo.model.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

typealias CocktailListState = ResourceState<List<Drink>>
typealias CocktailDetailState = ResourceState<Drink>

class CocktailsViewModel (
    private val cocktailsUseCase: GetCocktailsUseCase,
    private val cocktailsDetailUseCase: GetCocktailDetailUseCase
) : ViewModel() {

    private val cocktailMutableLiveData = MutableLiveData<CocktailListState>()
    private val cocktailDetailMutableLiveData = MutableLiveData<CocktailDetailState>()

    fun getCocktailLiveData(): LiveData<CocktailListState> {
        return cocktailMutableLiveData
    }

    fun getCocktailDetailLiveData() : LiveData<CocktailDetailState> {
        return cocktailDetailMutableLiveData
    }

    fun fetchCocktails() {
        cocktailMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = cocktailsUseCase.execute()

                withContext(Dispatchers.Main) {

                    cocktailMutableLiveData.value = ResourceState.Success(data)
                }
            } catch (e: Exception) {
                cocktailMutableLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
            }
        }
    }

    fun fetchCocktail(cocktailId: String) {
        cocktailDetailMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val data = cocktailsDetailUseCase.execute(cocktailId)

                withContext(Dispatchers.Main) {

                    cocktailDetailMutableLiveData.value = ResourceState.Success(data)
                }
            } catch (e: Exception) {
                cocktailDetailMutableLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
            }
        }
    }
}