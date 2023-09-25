package com.luridevlabs.koktelo.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.luridevlabs.koktelo.databinding.FragmentCocktailDetailBinding
import com.luridevlabs.koktelo.model.Drink
import com.luridevlabs.koktelo.model.ResourceState
import com.luridevlabs.koktelo.presentation.viewmodel.CocktailDetailState
import com.luridevlabs.koktelo.presentation.viewmodel.CocktailsViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CocktailDetailFragment : Fragment() {

    private val binding: FragmentCocktailDetailBinding by lazy {
        FragmentCocktailDetailBinding.inflate(layoutInflater)
    }

    private val args: CocktailDetailFragmentArgs by navArgs()

    private val cocktailsViewModel: CocktailsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        cocktailsViewModel.fetchCocktail(args.cocktailId)
    }

    private fun initViewModel() {

        cocktailsViewModel.getCocktailDetailLiveData().observe(viewLifecycleOwner) { state ->
            handleCocktailDetailState(state)
        }
    }

    private fun handleCocktailDetailState(state: CocktailDetailState) {
        when(state) {
            is ResourceState.Loading -> {
                binding.pbCocktailDetail.visibility = View.VISIBLE
            }
            is ResourceState.Success -> {
                binding.pbCocktailDetail.visibility = View.GONE
                initUI(state.result)
            }
            is ResourceState.Error -> {
                binding.pbCocktailDetail.visibility = View.GONE
                showErrorDialog(state.error)
            }
        }
    }

    private fun initUI(cocktail: Drink) {
        binding.tvCocktailDetailName.text = cocktail.drinkName
        //TODO: add all the fields

        Glide.with(requireContext())
            .load(cocktail.drinkImageUrl)
            .into(binding.ivCocktailImage)
    }

    private fun showErrorDialog(error: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(error)
            .setPositiveButton("Aceptar", null)
            .setNegativeButton("Reintentar") { dialog, witch ->
                cocktailsViewModel.fetchCocktails()
            }
    }
}