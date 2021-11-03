package com.epizy.fazrinursamsi.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.epizy.fazrinursamsi.kalkulator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var lastNumeric: Boolean = true
    var lastDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun onDigit(view: View) {
        if ((binding.resultId.text).startsWith("0") && !lastDot)
            binding.resultId.text = (view as Button).text
        else
            binding.resultId.append((view as Button).text)

        lastNumeric = true

    }

    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(binding.resultId.text.toString())) {
            binding.resultId.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }

    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-"))
            false
        else
            value.contains("/") || value.contains("+") || value.contains("-") || value.contains("*")
    }

    fun onDecimaldigit(view: View) {
        if (lastNumeric && !lastDot)
            binding.resultId.append(".")
        lastNumeric = false
        lastDot = true


    }

    fun onclear(view: View) {
        binding.resultId.text = ""
        lastNumeric = false
        lastDot = false

    }

    fun onEqual(view: View) {
        if (lastNumeric) {
            var value = binding.resultId.text.toString()
            var prefix = ""
            try {
                if(value.startsWith("-")){
                    prefix = "-"
                    value = value.substring(1)
                }
                if (value.contains("-")) {
                    val splitvalue = value.split("-")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    var result = one.toDouble() - two.toDouble()
                    binding.resultId.text = result.toString()
                }
                if (value.contains("+")) {
                    val splitvalue = value.split("+")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() + two.toDouble()
                    binding.resultId.text = result.toString()
                }
                if (value.contains("*")) {
                    val splitvalue = value.split("*")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() * two.toDouble()
                    binding.resultId.text = result.toString()
                }
                if (value.contains("/")) {
                    val splitvalue = value.split("/")
                    var one = splitvalue[0]
                    var two = splitvalue[1]
                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    var result = one.toDouble() / two.toDouble()
                    binding.resultId.text = result.toString()
                }
            } catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }

    }
}