package com.example.moliyafinance.navigation

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.adapters.AdapterTransaksi
import com.example.moliyafinance.databinding.FragmentHomeBinding
import com.example.moliyafinance.models.User
import com.example.moliyafinance.models.getTransaksi
import com.example.moliyafinance.models.showToast
import com.example.moliyafinance.pages.TambahTransaksi

class Home : Fragment() {
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
        bind.swipe.setOnRefreshListener {
            init()
        }
        init()
    }

    override fun onResume() {
        super.onResume()
        init()
        initClicks()
    }

    private fun init() {
        bind.swipe.isRefreshing = true
        User.getUserData(requireContext(), onResult = { data ->
            run {
                if (data != null) {
                    if (isAdded) {
                        User.userData = data
                        bind.nama.text = data.nama

                        getTransaksi(requireContext(), onResult = { list ->
                            run {
                                if (isAdded) {
                                    val adapter = AdapterTransaksi(requireContext(), list)
                                    fadeIn(bind.recycler)
                                    bind.recycler.adapter = adapter
                                    bind.recycler.layoutManager = LinearLayoutManager(requireContext())
                                    bind.swipe.isRefreshing = false
                                }
                            }
                        }, onError = {
                            bind.swipe.isRefreshing = false
                            showToast(requireContext(), "Terjadi kesalahan")
                        })
                    }
                }

            }
        })
        bind.nama.text = User.userData.nama
    }

    private fun initClicks(){
        bind.tambahTransaksi.setOnClickListener {
            Dashboard.editing = false
            val i = Intent(requireContext(), TambahTransaksi::class.java)
            startActivity(i)
        }
    }

    private fun fadeIn(view: View) {
        view.alpha = 0f
        view.visibility = View.VISIBLE
        ObjectAnimator.ofFloat(view, "alpha", 0f, 1f).apply {
            duration = 500 // Duration of the animation in milliseconds
            start()
        }
    }
}