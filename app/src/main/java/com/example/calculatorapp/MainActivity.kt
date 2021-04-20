package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onDigit(view: View) {
        inputTv.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        inputTv.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if(lastNumeric && !lastDot) {
            inputTv.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        if(lastNumeric && !isOperatorAdded(inputTv.text.toString())) {
            inputTv.append((view as Button).text)
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View) {
        if(lastNumeric) {
            var valueTv = inputTv.text.toString()
            var prefix = ""

            try {

                if(valueTv.startsWith("-")) {
                    prefix = "-"
                    valueTv = valueTv.substring(1)
                }

                if(valueTv.contains("-")) {
                    val splitValue = valueTv.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    inputTv.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }

                //Addition
                if(valueTv.contains("+")) {
                    val splitValue = valueTv.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    inputTv.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                }

                //Multiply
                if(valueTv.contains("*")) {
                    val splitValue = valueTv.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    inputTv.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                }

                //Division
                if(valueTv.contains("/")) {
                    val splitValue = valueTv.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    inputTv.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }

            }catch (e: ArrayStoreException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String {
        var value = result
        if(result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }

    private fun isOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("-")){
            false
        }
        else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}