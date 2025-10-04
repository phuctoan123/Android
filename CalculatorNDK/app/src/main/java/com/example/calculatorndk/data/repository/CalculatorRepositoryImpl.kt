package com.example.calculatorndk.data.repository

import com.example.calculatorndk.data.source.ndk.NdkSource
import com.example.calculatorndk.domain.model.CalculationResult
import com.example.calculatorndk.domain.repository.CalculatorRepository

class CalculatorRepositoryImpl(
    private val ndkSource: NdkSource
) : CalculatorRepository {
    override fun calculateResult(
        value1: Double,
        value2: Double,
        operator: Char
    ): CalculationResult {
        val result = ndkSource.calculateResult(value1, value2, operator)

        val resultNumber = result.toDoubleOrNull()
        return if (resultNumber != null) {
            CalculationResult(resultNumber.toString())
        } else {
            CalculationResult(result)
        }
    }

}