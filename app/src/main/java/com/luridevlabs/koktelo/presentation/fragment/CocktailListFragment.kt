package com.luridevlabs.koktelo.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luridevlabs.koktelo.databinding.FragmentCocktailListBinding

class CocktailListFragment : Fragment() {

    private val binding: FragmentCocktailListBinding by lazy {
        FragmentCocktailListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}