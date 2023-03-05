package com.example.hw2

import android.widget.EditText

class HomeworkCalculator
    (
    hwList: ArrayList<Double>,
    m1: Double,
    m2: Double,
    participation: Double,
    groupPresentation: Double,
    groupProject: Double
) {
    private var hwList = hwList
    private var m1 = m1
    private var m2 = m2
    private var participation = participation
    private var groupPresentation = groupPresentation
    private var groupProject = groupProject

    fun calculateGrade(): Double {
        return 0.1 * participation + 0.1 * m1 + 0.2 * m2 + 0.1 * groupPresentation +
                0.3 * groupProject + 0.2 * (sumList(hwList))
    }

    private fun sumList(list: List<Double>): Double {
        var sum = 0.0
        for (num in list) {
            sum += num
        }
        return sum / hwList.size
    }




}