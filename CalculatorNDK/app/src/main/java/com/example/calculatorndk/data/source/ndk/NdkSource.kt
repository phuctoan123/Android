package com.example.calculatorndk.data.source.ndk

interface NdkSource {
    fun calculateResult(value1: Double, value2: Double, operator: Char): String
}