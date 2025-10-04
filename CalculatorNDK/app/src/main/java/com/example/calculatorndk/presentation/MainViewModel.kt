package com.example.calculatorndk.presentation

import androidx.lifecycle.ViewModel
import com.example.calculatorndk.data.repository.CalculatorRepositoryImpl
import com.example.calculatorndk.data.source.ndk.NdkSource
import com.example.calculatorndk.data.source.ndk.NdkSourceImpl
import com.example.calculatorndk.domain.repository.CalculatorRepository
import com.example.calculatorndk.domain.use_cases.CalculateResultUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class MainState(
    val result: String = "",

)

sealed class MainEvent {
    data class OnNumClicked(val number: String) : MainEvent()
    data class OnOperatorClicked(val operator: String) : MainEvent()
    data object OnBackClicked: MainEvent()
    data object OnClearClicked: MainEvent()
    data object OnEqualsClicked: MainEvent()

}

class MainViewModel: ViewModel() {

    val calculateResultUseCase = CalculateResultUseCase(
        CalculatorRepositoryImpl(
            NdkSourceImpl()
        )

    )
    private val _mainState = MutableStateFlow(MainState())
    val mainState: StateFlow<MainState> = _mainState

    fun onEvent(mainEvent: MainEvent) {
        when(mainEvent){
            MainEvent.OnBackClicked -> _mainState.update {
                it.copy(
                    result = it.result.dropLast(1)
                )
            }
            MainEvent.OnClearClicked -> _mainState.update {
                it.copy(
                    result = ""
                )
            }
            MainEvent.OnEqualsClicked -> {
                val existingValue = mainState.value.result

                val result = existingValue.split("+", "-", "*", "/").map { it.toDouble() }
                val operator = existingValue.filter { it == '+' || it == '-' || it == '*' || it == '/' }

                if (result.size < 2) return

                val calculatedResult = calculateResultUseCase(
                    result[0],
                    result[1],
                    operator[0]
                )
                _mainState.update {
                    it.copy(
                        result = calculatedResult.result
                    )
                }

            }
            is MainEvent.OnNumClicked -> {
                val existingValue = mainState.value.result

                _mainState.update {
                    it.copy(
                        result = existingValue + mainEvent.number
                    )
                }

            }
            is MainEvent.OnOperatorClicked -> {
                val existingValue = mainState.value.result

                _mainState.update {
                    it.copy(
                        result = existingValue + mainEvent.operator
                    )
                }
            }
        }
    }


}