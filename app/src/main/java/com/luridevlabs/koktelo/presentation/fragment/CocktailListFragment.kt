package com.luridevlabs.koktelo.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.luridevlabs.koktelo.R
import com.luridevlabs.koktelo.databinding.FragmentCocktailListBinding
import com.luridevlabs.koktelo.model.ResourceState
import com.luridevlabs.koktelo.presentation.adapter.CocktailListAdapter
import com.luridevlabs.koktelo.presentation.viewmodel.CocktailDetailState
import com.luridevlabs.koktelo.presentation.viewmodel.CocktailListState
import com.luridevlabs.koktelo.presentation.viewmodel.CocktailsViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CocktailListFragment : Fragment() {

    private val binding: FragmentCocktailListBinding by lazy {
        FragmentCocktailListBinding.inflate(layoutInflater)
    }

    private val cocktailListAdapter = CocktailListAdapter()

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
        initUI()

        cocktailsViewModel.fetchCocktails()
    }

    private fun initViewModel() {

        cocktailsViewModel.getCocktailLiveData().observe(viewLifecycleOwner) { state ->
            handleCocktailListState(state)
        }
    }

    private fun handleCocktailListState(state: CocktailListState) {
        when(state) {
            is ResourceState.Loading -> {
                binding.pbCocktailList.visibility = View.VISIBLE
            }
            is ResourceState.Success -> {
                binding.pbCocktailList.visibility = View.GONE
                cocktailListAdapter.submitList(state.result)
            }
            is ResourceState.Error -> {
                binding.pbCocktailList.visibility = View.GONE
                showErrorDialog(state.error)
            }
        }
    }

    private fun initUI() {
        binding.rvCocktailList.adapter = cocktailListAdapter
        binding.rvCocktailList.layoutManager = LinearLayoutManager(requireContext())

        cocktailListAdapter.onClickListener = { cocktail ->

            findNavController().navigate(
                CocktailListFragmentDirections.actionCocktailListFragmentToCocktailDetailFragment(cocktail.drinkId)
            )
        }
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