package com.example.moliyafinance.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moliyafinance.databinding.FragmentLaporanBinding
import com.example.moliyafinance.laporan.ArusKas
import com.example.moliyafinance.laporan.BebanOperasional
import com.example.moliyafinance.laporan.BukuBesar
import com.example.moliyafinance.laporan.HutangPiutang
import com.example.moliyafinance.laporan.JurnalUmum
import com.example.moliyafinance.laporan.LabaRugi
import com.example.moliyafinance.laporan.Neraca
import com.example.moliyafinance.laporan.NeracaSaldo
import com.example.moliyafinance.laporan.Periode
import com.example.moliyafinance.laporan.PerubahanModal

class Laporan : Fragment() {
    private lateinit var bind: FragmentLaporanBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentLaporanBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }

    private fun initClicks() {
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
        bind.labaRugi.setOnClickListener {
            val i = Intent(requireContext(), LabaRugi::class.java)
            requireContext().startActivity(i)
        }
        bind.perubahanModal.setOnClickListener {
            val i = Intent(requireContext(), PerubahanModal::class.java)
            requireContext().startActivity(i)
        }
        bind.neraca.setOnClickListener {
            val i = Intent(requireContext(), Neraca::class.java)
            requireContext().startActivity(i)
        }
        bind.periode.setOnClickListener {
            val i = Intent(requireContext(), Periode::class.java)
            requireContext().startActivity(i)
        }
        bind.arusKas.setOnClickListener {
            val i = Intent(requireContext(), ArusKas::class.java)
            requireContext().startActivity(i)
        }
        bind.hutangPiutang.setOnClickListener {
            val i = Intent(requireContext(), HutangPiutang::class.java)
            requireContext().startActivity(i)
        }
        bind.bebanOperasional.setOnClickListener {
            val i = Intent(requireContext(), BebanOperasional::class.java)
            requireContext().startActivity(i)
        }
    }
}