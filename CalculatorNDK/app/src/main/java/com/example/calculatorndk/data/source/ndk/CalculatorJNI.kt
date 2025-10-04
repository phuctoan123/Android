package com.example.calculatorndk.data.source.ndk

object CalculatorJNI {
    init {
        System.loadLibrary("calculatorndk")
    }

    external fun calculateResult(value1: Double, value2: Double, operator: Char):String
}