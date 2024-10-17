package com.example.moliyafinance.pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moliyafinance.objects.Variables
import com.example.moliyafinance.objects.Variables.akunList
import com.example.moliyafinance.adapters.AdapterDaftarAkun
import com.example.moliyafinance.databinding.ActivityDaftarAkunBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DaftarAkun : AppCompatActivity() {
    private lateinit var bind: ActivityDaftarAkunBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDaftarAkunBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.progressBar.visibility = View.VISIBLE
        bind.recycler.visibility = View.GONE
        CoroutineScope(Dispatchers.Main).launch {
            init()
            initSearch()
            bind.progressBar.visibility = View.GONE
            bind.recycler.visibility = View.VISIBLE
        }
    }

    private fun init() {
        bind.back.setOnClickListener {
            finish()
        }
        val adapter = AdapterDaftarAkun(akunList)
        bind.recycler.adapter = adapter
        bind.recycler.layoutManager = LinearLayoutManager(this)

    }

    private fun initSearch() {
        bind.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filterSearch(s.toString())
                if (s.toString().isBlank()) {
                    init()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterSearch(s: String) {
        val newData = ArrayList<Variables.DataAkun>()
        val adapter = AdapterDaftarAkun(newData)
        bind.recycler.adapter = adapter
        for (data in akunList) {
            if (data.nama.lowercase().contains(s.lowercase())) {
                newData.add(data)
            }
        }
        adapter.notifyDataSetChanged()
    }
}