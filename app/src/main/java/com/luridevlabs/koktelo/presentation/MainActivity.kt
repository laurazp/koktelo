package com.luridevlabs.koktelo.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.luridevlabs.koktelo.R
import com.luridevlabs.koktelo.databinding.ActivityMainBinding
import com.luridevlabs.koktelo.presentation.fragment.TabFragment

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadFragment(TabFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fcv_main_container, fragment).addToBackStack(null).commit()
    }

}