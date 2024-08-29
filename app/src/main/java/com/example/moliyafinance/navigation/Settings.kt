package com.example.moliyafinance.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moliyafinance.databinding.FragmentSettingsBinding
import com.example.moliyafinance.pages.Login
import com.google.firebase.auth.FirebaseAuth

class Settings : Fragment(){
    private lateinit var bind : FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentSettingsBinding.inflate(layoutInflater,container,false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicks()
    }
    private fun initClicks() {
        bind.keluar.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            if(auth.currentUser!=null){
                auth.signOut()
                val i = Intent(requireContext(),Login::class.java)
                startActivity(i)
                activity?.finish()
            }
        }

    }
}