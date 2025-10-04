package com.example.calculatorndk.domain.use_cases

import com.example.calculatorndk.domain.model.CalculationResult
import com.example.calculatorndk.domain.repository.CalculatorRepository

class CalculateResultUseCase (
    private val calculatorRepository: CalculatorRepository
) {
    operator fun invoke(
        value1: Double,
        value2: Double,
        operator: Char
    ): CalculationResult {
        return calculatorRepository.calculateResult(value1, value2, operator)
    }


}