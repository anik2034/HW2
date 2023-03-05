package com.example.hw2

import android.widget.EditText

class Validation {
    fun put100(editText: EditText){
        if(editText.text.toString().length == 0){
            editText.setText("100")
        }
    }
    fun inBounds(n: Double): Boolean {
        return (n >= 0 && n <= 100)


    }
    fun double2string(list: List<Double>): ArrayList<String>{
        var strings = ArrayList<String>()
        for(i in list.indices){
            strings.add("Homework"+(i+1)+" = "+list.get(i))
        }
        return strings
    }
}