package com.example.moliyafinance.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moliyafinance.databinding.FragmentMasterDataBinding
import com.example.moliyafinance.pages.DaftarAkun

class MasterData : Fragment() {
    private lateinit var bind: FragmentMasterDataBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMasterDataBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        bind.daftarAkun.setOnClickListener {
            startActivity(Intent(requireContext(), DaftarAkun::class.java))
        }
    }
}