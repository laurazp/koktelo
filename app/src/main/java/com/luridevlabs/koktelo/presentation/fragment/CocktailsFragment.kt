package com.luridevlabs.koktelo.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luridevlabs.koktelo.R
import com.luridevlabs.koktelo.databinding.FragmentCocktailsBinding
import com.luridevlabs.koktelo.databinding.FragmentTabBinding

class CocktailsFragment : Fragment() {

    private val binding: FragmentCocktailsBinding by lazy {
        FragmentCocktailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}