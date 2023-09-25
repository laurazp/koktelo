package com.luridevlabs.koktelo.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.luridevlabs.koktelo.R
import com.luridevlabs.koktelo.databinding.FragmentTabBinding
import java.lang.IllegalArgumentException

class TabFragment : Fragment() {

    private val binding: FragmentTabBinding by lazy {
        FragmentTabBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        val viewPager = binding.vp2CocktailViewpager
        val tabLayout = binding.tlCocktailTabs

        viewPager.adapter = CocktailViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.home_fragment)
                1 -> getString(R.string.cocktails_fragment)
                else -> getString(R.string.favorites_fragment)
            }
        }.attach()
    }

    private inner class CocktailViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount() = 3

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> HomeFragment()
                1 -> CocktailListFragment()
                2 -> FavoritesFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }
}