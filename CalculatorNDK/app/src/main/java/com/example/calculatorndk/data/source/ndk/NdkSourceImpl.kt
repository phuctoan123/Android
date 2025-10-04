package com.example.calculatorndk.data.source.ndk

class NdkSourceImpl : NdkSource {
    override fun calculateResult(
        value1: Double,
        value2: Double,
        operator: Char
    ): String {
        return CalculatorJNI.calculateResult(value1, value2, operator)
    }

}