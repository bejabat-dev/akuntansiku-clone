package com.example.moliyafinance.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moliyafinance.databinding.FragmentProfilBinding

class Profil : Fragment(){
    private lateinit var bind : FragmentProfilBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentProfilBinding.inflate(layoutInflater,container,false)
        return bind.root
    }
}