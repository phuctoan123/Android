package com.example.calculatorndk.domain.repository

import com.example.calculatorndk.domain.model.CalculationResult

interface CalculatorRepository {
    fun calculateResult(
        value1: Double,
        value2: Double,
        operator: Char

    ): CalculationResult
}