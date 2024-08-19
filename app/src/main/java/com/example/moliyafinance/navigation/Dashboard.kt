package com.example.moliyafinance.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.moliyafinance.R
import com.example.moliyafinance.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {
    private lateinit var bind: ActivityDashboardBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        fragmentManager = supportFragmentManager
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                .replace(R.id.dashboard_fragment, Home())
                .commit()
        }
        bind.bottomNav.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment? = when (item.itemId) {
                R.id.nav_home -> Home()
                R.id.nav_history -> History()
                R.id.nav_settings -> Settings()
                R.id.nav_graph -> Chart()
                else -> null
            }
            selectedFragment?.let {
                fragmentManager.beginTransaction()
                    .replace(R.id.dashboard_fragment, it)
                    .commit()
            }
            true
        }
    }
}