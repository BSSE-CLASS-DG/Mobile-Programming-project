package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.calculator.databinding.ActivityMainBinding
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private  var canAddOperator = false
    private  var canAddDecimal = true
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun allClearAction(view: View) {
        binding.workingsTV.text = ""
        binding.resultsTV.text = ""
    }

    fun backSpaceAction(view: View) {

        val length = binding.workingsTV.length()
        if(length>0){
            binding.workingsTV.text = binding.workingsTV.text.subSequence(0,length-1)
        }
    }

    fun equalsAction(view: View) {
        binding.resultsTV.text  = calculateResults()
    }


    fun numberAction(view: View) {
        if(view is Button){
            if(view.text == "."){
                if(canAddDecimal){
                    binding.workingsTV.append(view.text)
                }
            canAddDecimal = false
            }else{
                binding.workingsTV.append(view.text)
            }

            canAddOperator = true
        }
    }

    fun operatorAction(view: View){
        if(view is Button && canAddOperator){
            binding.workingsTV.append(view.text)
            canAddOperator = false
            canAddDecimal = true
        }
    }

    private fun calculateResults(): String
    {
        val digitsOperators = digitsOperators()
        if(digitsOperators.isEmpty()) return ""

        val timesDivision = timesDivisionCalculate(digitsOperators)
        if(timesDivision.isEmpty()) return ""

        val result = addSubtractCalculate(timesDivision)
        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Float
    {
        var result = passedList[0] as Float

        for(i in passedList.indices)
        {
            if(passedList[i] is Char && i != passedList.lastIndex)
            {
                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (operator == '+')
                    result += nextDigit
                if (operator == '-')
                    result -= nextDigit
            }
        }

        return result
    }

    private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any>
    {
        var list = passedList
        while (list.contains('x') || list.contains('/') || list.contains('%'))
        {
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any>
    {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for(i in passedList.indices)
        {
            if(passedList[i] is Char && i != passedList.lastIndex && i < restartIndex)
            {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float
                when(operator)
                {
                    'x' ->
                    {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' ->
                    {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    '%' ->
                    {
                        newList.add(prevDigit.mod(nextDigit))
                        restartIndex = i+1
                    }
                    else ->
                    {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if(i > restartIndex)
                newList.add(passedList[i])
        }

        return newList
    }

    private fun digitsOperators(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentNumber = ""
        for (character in binding.workingsTV.text) {
            if (character.isDigit() || character == '.') {
                currentNumber += character
            } else if (character == '(' || character == ')') {
                if (currentNumber.isNotEmpty()) {
                    list.add(currentNumber.toFloat())
                    currentNumber = ""
                }
                list.add(character)
            } else {
                if (currentNumber.isNotEmpty()) {
                    list.add(currentNumber.toFloat())
                    currentNumber = ""
                }
                list.add(character)
            }
        }

        if (currentNumber.isNotEmpty()) {
            list.add(currentNumber.toFloat())
        }

        return list
    }

    private fun bracketAction(view: View) {
        if (view is Button) {
            val bracket = view.text.toString()
            binding.workingsTV.append(bracket)

            // If an opening bracket is added, allow adding operators after it
            if (bracket == "(") {
                canAddOperator = true
            }
        }
    }


}
