package com.example.moliyafinance.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moliyafinance.databinding.FragmentReportBinding
import com.example.moliyafinance.laporan.BukuBesar
import com.example.moliyafinance.laporan.JurnalUmum
import com.example.moliyafinance.laporan.NeracaSaldo

class Report : Fragment() {
    private lateinit var bind: FragmentReportBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentReportBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.jurnalUmum.setOnClickListener {
            val i = Intent(requireContext(), JurnalUmum::class.java)
            requireContext().startActivity(i)
        }
        bind.bukuBesar.setOnClickListener {
            val i = Intent(requireContext(), BukuBesar::class.java)
            requireContext().startActivity(i)
        }
        bind.neracaSaldo.setOnClickListener {
            val i = Intent(requireContext(), NeracaSaldo::class.java)
            requireContext().startActivity(i)
        }
    }
}