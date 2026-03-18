package com.fake.forloopbasic

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.collections.sorted

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNumbers = findViewById<EditText>(R.id.etNumbers)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val tvAverage = findViewById<TextView>(R.id.tvAverage)
        val tvMedian = findViewById<TextView>(R.id.tvMedian)
        val tvMode = findViewById<TextView>(R.id.tvMode)
        val tvHighest = findViewById<TextView>(R.id.tvHighest)
        val tvLowest = findViewById<TextView>(R.id.tvLowest)

        var mean: Double = 0.00
        var sum: Double = 0.00
        var median: Double = 0.00
        var mode: Double = 0.00

        btnCalculate.setOnClickListener {
            val arrNumbers = etNumbers.text.toString().split(",").map { it.trim().toDouble() }
            if (!arrNumbers.isEmpty()) {
                var counter: Int = 0
                var temp = 0
                for (x in 1..arrNumbers.size) {
                    sum += x
                }
                mean = sum / arrNumbers.size

                val numList = arrNumbers.sortedBy { it }
                if (numList.size % 2 != 0) {
                    median = arrNumbers[numList.size / 2]
                } else {
                    var mid1 = arrNumbers[numList.size / 2]
                    var mid2 = arrNumbers[numList.size / 2 - 1]
                    median = mid1 + mid2
                }

                for (x in 0..arrNumbers.size - 1) {
                    for (y in 0..arrNumbers.size - 1) {
                        if (arrNumbers[x] == arrNumbers[y]) {
                            counter++
                        }
                    }
                    if (counter > temp) {
                        temp = counter
                        mode = arrNumbers[x]
                    }
                    counter = 0
                }

                tvTotal.text = sum.toString()
                tvAverage.text = mean.toString()
                tvMedian.text = median.toString()
                tvMode.text = mode.toString()
                tvHighest.text = arrNumbers.max().toString()
                tvLowest.text = arrNumbers.min().toString()
            }

        }
    }
    }