package com.example.moliyafinance.navigation

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.Utils
import com.example.moliyafinance.adapters.AdapterTransaksi
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.databinding.FragmentHomeBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.objects.LoadingDialog
import com.example.moliyafinance.objects.User
import com.example.moliyafinance.pages.TambahTransaksi

class Home : Fragment() {
    private lateinit var bind: FragmentHomeBinding
    private lateinit var adapter: AdapterTransaksi
    private var firstLaunch = true
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
        if (firstLaunch) {
            LoadingDialog.showDialog(requireContext(), "Memuat")
            init()
        }
    }

    override fun onResume() {
        super.onResume()
        init()
        initClicks()
        initSearch()
    }

    private fun initSearch() {
        bind.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s.toString().isEmpty()) {
                    adapter = AdapterTransaksi(requireContext(), Dashboard.listTransaksi)
                    bind.recycler.adapter = adapter
                    return
                }

                val filteredList = ArrayList<Transaksi>()
                for (i in Dashboard.listTransaksi) {
                    if (i.catatan.lowercase().contains(s.toString().lowercase())) {
                        filteredList.add(i)
                    }
                }
                adapter = AdapterTransaksi(requireContext(), filteredList)
                bind.recycler.adapter = adapter
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun init() {

        bind.swipe.isRefreshing = true
        User.getUserData(requireContext(), onResult = { data ->
            run {
                if (data != null) {
                    if (isAdded) {
                        User.userData = data
                        Dashboard.userData = data
                        bind.nama.text = data.nama

                        Utils().getTransaksi(requireContext(), onResult = { list ->
                            run {
                                if (isAdded) {
                                    Dashboard.isLoaded = true
                                    Dashboard.listTransaksi = list
                                    adapter = AdapterTransaksi(requireContext(), list)
                                    Utils().fadeIn(bind.recycler)
                                    bind.recycler.adapter = adapter
                                    bind.recycler.layoutManager =
                                        LinearLayoutManager(requireContext())
                                    bind.swipe.isRefreshing = false
                                    if (firstLaunch) {
                                        firstLaunch = false
                                        LoadingDialog.dialog.dismiss()
                                    }
                                    if (Dashboard.date.isNotEmpty()) {
                                        bind.nama.text = Dashboard.userData.nama
                                        bind.tanggal.text = Dashboard.date
                                        LoadingDialog.dialog.dismiss()
                                        val filteredTransaksi = ArrayList<Transaksi>()
                                        for (newData in Dashboard.listTransaksi) {
                                            if (newData.timestamp!! >= Dashboard.startDate!! && newData.timestamp <= Dashboard.endDate!!) {
                                                filteredTransaksi.add(newData)
                                            }
                                        }
                                        adapter =
                                            AdapterTransaksi(requireContext(), filteredTransaksi)
                                        bind.recycler.adapter = adapter
                                    }
                                }
                            }
                        }, onError = {
                            if (firstLaunch) {
                                LoadingDialog.dialog.dismiss()
                            }
                            bind.swipe.isRefreshing = false
                            Utils().showToast(requireContext(), "Terjadi kesalahan")
                        })
                    }
                }

            }
        })
        bind.nama.text = User.userData.nama
    }

    private fun initClicks() {
        bind.pilihTanggal.setOnClickListener {
            val dialogTanggalBinding = DialogTanggalBinding.inflate(layoutInflater)
            Utils().showDateDialog(requireContext(), dialogTanggalBinding)
        }
        bind.tambahTransaksi.setOnClickListener {
            Dashboard.editing = false
            val i = Intent(requireContext(), TambahTransaksi::class.java)
            startActivity(i)
        }
    }
}