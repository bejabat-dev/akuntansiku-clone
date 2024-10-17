package com.example.moliyafinance.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moliyafinance.databinding.FragmentChartBinding

class Chart : Fragment() {
    private lateinit var bind: FragmentChartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        bind = FragmentChartBinding.inflate(layoutInflater)
        return bind.root
    }
}