package com.example.moliyafinance.navigation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.moliyafinance.R
import com.example.moliyafinance.Utils
import com.example.moliyafinance.adapters.AdapterTransaksi
import com.example.moliyafinance.databinding.ActivityDashboardBinding
import com.example.moliyafinance.databinding.DialogTanggalBinding
import com.example.moliyafinance.models.Transaksi
import com.example.moliyafinance.models.User

import com.google.firebase.Timestamp
import java.util.Calendar

class Dashboard : AppCompatActivity() {
    private lateinit var bind: ActivityDashboardBinding
    private lateinit var fragmentManager: FragmentManager

    companion object {
        var editing = false
        lateinit var listTransaksi: List<Transaksi>
        var userData = User()
        var isLoaded = false

        fun showDialog(
            context: Context,
            dialogBinding: DialogTanggalBinding,
            adapter: AdapterTransaksi
        ) {
            var startDateTimestamp: Timestamp? = null
            var endDateTimestamp: Timestamp? = null
            val b = AlertDialog.Builder(context)
            b.setView(dialogBinding.root)

            dialogBinding.start.init(
                dialogBinding.start.year, dialogBinding.start.month, dialogBinding.start.dayOfMonth
            ) { _, year, month, day ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, day, 0, 0, 0) // Set time to 00:00:00 for start of day
                startDateTimestamp = Timestamp(calendar.time)
            }

            dialogBinding.end.init(
                dialogBinding.end.year, dialogBinding.end.month, dialogBinding.end.dayOfMonth
            ) { _, year, month, day ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, day, 23, 59, 59) // Set time to 23:59:59 for end of day
                endDateTimestamp = Timestamp(calendar.time)
            }

            val dialog = b.create()
            dialog.show()

            dialogBinding.simpan.setOnClickListener {
                if (startDateTimestamp != null && endDateTimestamp != null) {
                    Utils().getFilteredTransaksi(
                        context,
                        startDateTimestamp!!,
                        endDateTimestamp!!,
                        adapter
                    )
                    println("Start: $startDateTimestamp End: $endDateTimestamp")
                } else {
                    println("Error: Start or End date is not selected")
                }
                dialog.dismiss()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(bind.root)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        fragmentManager = supportFragmentManager
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().replace(R.id.dashboard_fragment, Home()).commit()
        }
        bind.bottomNav.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment? = when (item.itemId) {
                R.id.nav_home -> Home()
                R.id.nav_report -> Laporan()
                R.id.nav_master_data -> MasterData()
                R.id.nav_settings -> Pengaturan()
                R.id.nav_graph -> Chart()
                else -> null
            }
            selectedFragment?.let {
                fragmentManager.beginTransaction().replace(R.id.dashboard_fragment, it).commit()
            }
            true
        }
    }
}