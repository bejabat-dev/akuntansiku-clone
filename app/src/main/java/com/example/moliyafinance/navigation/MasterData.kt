package com.example.moliyafinance.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moliyafinance.databinding.FragmentMasterDataBinding

class MasterData : Fragment(){
    private lateinit var bind : FragmentMasterDataBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMasterDataBinding.inflate(layoutInflater)
        return bind.root
    }

    private fun init(){

    }
}