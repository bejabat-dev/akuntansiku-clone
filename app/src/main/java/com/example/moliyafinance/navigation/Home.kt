package com.example.moliyafinance.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moliyafinance.databinding.FragmentHomeBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.pages.TambahTransaksi

class Home : Fragment() {
    companion object{
        lateinit var transaksi : Transaksi
    }
    private lateinit var bind: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.tambahTransaksi.setOnClickListener {
            val i = Intent(requireContext(),TambahTransaksi::class.java)
            startActivity(i)
        }
    }
}