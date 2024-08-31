package com.example.moliyafinance.navigation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moliyafinance.Colors
import com.example.moliyafinance.databinding.FragmentChartBinding
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.github.mikephil.charting.formatter.ValueFormatter

class Chart : Fragment() {
    private lateinit var bind: FragmentChartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentChartBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initKasDanBank()
        initPiutang()
        initHutang()
    }

    private fun initKasDanBank() {
        val entries = mutableListOf<Entry>()
        entries.add(Entry(1f, 1500000f))
        entries.add(Entry(2f, 2500000f))
        entries.add(Entry(3f, 4000000f))
        entries.add(Entry(4f, 6700000f))
        entries.add(Entry(5f, 1250000f))
        entries.add(Entry(6f, 3400000f))

        val dataset = ScatterDataSet(entries, "Kas dan Bank")
        dataset.scatterShapeSize = 10f
        dataset.color = Colors.blue
        dataset.setDrawValues(false)
        dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE)

        val data = ScatterData(dataset)

        bind.kasDanBank.data = data
        bind.kasDanBank.xAxis.setDrawGridLines(false)
        bind.kasDanBank.axisLeft.setDrawGridLines(false)
        bind.kasDanBank.axisRight.setDrawGridLines(false)
        bind.kasDanBank.axisRight.isEnabled = false
        bind.kasDanBank.xAxis.position = XAxis.XAxisPosition.BOTTOM
        bind.kasDanBank.xAxis.labelRotationAngle = 0f
        bind.kasDanBank.setNoDataText("Tidak ada data")
        bind.kasDanBank.setNoDataTextColor(Colors.blue)


        val description = Description().apply {
            text = ""
            textSize = 12f
            textColor = Color.BLACK
        }
        bind.kasDanBank.description = description

        bind.kasDanBank.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return when (value.toInt()) {
                    1 -> "Jan"
                    2 -> "Feb"
                    3 -> "Mar"
                    4 -> "Apr"
                    5 -> "May"
                    6 -> "Jun"
                    7 -> "Jul"
                    8 -> "Agu"
                    9 -> "Sep"
                    10 -> "Okt"
                    11 -> "Nov"
                    12 -> "Des"
                    else -> ""
                }
            }
        }
        bind.kasDanBank.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return "${value.toInt()}"
            }
        }
        bind.kasDanBank.invalidate()
    }

    private fun initPiutang() {
        val entries = mutableListOf<Entry>()
        entries.add(Entry(1f, 1500000f))
        entries.add(Entry(2f, 2500000f))
        entries.add(Entry(3f, 4000000f))
        entries.add(Entry(4f, 6700000f))
        entries.add(Entry(5f, 1250000f))
        entries.add(Entry(6f, 3400000f))

        val dataset = ScatterDataSet(entries, "Kas dan Bank")
        dataset.scatterShapeSize = 10f
        dataset.color = Colors.blue
        dataset.setDrawValues(false)
        dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE)

        val data = ScatterData(dataset)
        bind.piutang.data = data
        bind.piutang.xAxis.setDrawGridLines(false)
        bind.piutang.axisLeft.setDrawGridLines(false)
        bind.piutang.axisRight.setDrawGridLines(false)
        bind.piutang.axisRight.isEnabled = false
        bind.piutang.xAxis.position = XAxis.XAxisPosition.BOTTOM
        bind.piutang.xAxis.labelRotationAngle = 0f
        bind.piutang.setNoDataText("Tidak ada data")
        bind.piutang.setNoDataTextColor(Colors.blue)

        val description = Description().apply {
            text = ""
            textSize = 12f
            textColor = Color.BLACK
        }
        bind.piutang.description = description

        bind.piutang.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return when (value.toInt()) {
                    1 -> "Jan"
                    2 -> "Feb"
                    3 -> "Mar"
                    4 -> "Apr"
                    5 -> "May"
                    6 -> "Jun"
                    7 -> "Jul"
                    8 -> "Agu"
                    9 -> "Sep"
                    10 -> "Okt"
                    11 -> "Nov"
                    12 -> "Des"
                    else -> ""
                }
            }
        }
        bind.piutang.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return "${value.toInt()}"
            }
        }
        bind.piutang.invalidate()
    }

    private fun initHutang() {
        val entries = mutableListOf<Entry>()
        entries.add(Entry(1f, 1500000f))
        entries.add(Entry(2f, 2500000f))
        entries.add(Entry(3f, 4000000f))
        entries.add(Entry(4f, 6700000f))
        entries.add(Entry(5f, 1250000f))
        entries.add(Entry(6f, 3400000f))

        val dataset = ScatterDataSet(entries, "Kas dan Bank")
        dataset.scatterShapeSize = 10f
        dataset.color = Colors.blue
        dataset.setDrawValues(false)
        dataset.setScatterShape(ScatterChart.ScatterShape.CIRCLE)

        val data = ScatterData(dataset)
        bind.hutang.data = data
        bind.hutang.xAxis.setDrawGridLines(false)
        bind.hutang.axisLeft.setDrawGridLines(false)
        bind.hutang.axisRight.setDrawGridLines(false)
        bind.hutang.axisRight.isEnabled = false
        bind.hutang.xAxis.position = XAxis.XAxisPosition.BOTTOM
        bind.hutang.xAxis.labelRotationAngle = 0f
        bind.hutang.setNoDataText("Tidak ada data")
        bind.hutang.setNoDataTextColor(Colors.blue)

        val description = Description().apply {
            text = ""
            textSize = 12f
            textColor = Color.BLACK
        }
        bind.hutang.description = description

        bind.hutang.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return when (value.toInt()) {
                    1 -> "Jan"
                    2 -> "Feb"
                    3 -> "Mar"
                    4 -> "Apr"
                    5 -> "May"
                    6 -> "Jun"
                    7 -> "Jul"
                    8 -> "Agu"
                    9 -> "Sep"
                    10 -> "Okt"
                    11 -> "Nov"
                    12 -> "Des"
                    else -> ""
                }
            }
        }
        bind.hutang.axisLeft.valueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return "${value.toInt()}"
            }
        }
        bind.hutang.invalidate()
    }
}