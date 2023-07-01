package ru.gnusinay.windyapp.flowfeature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.gnusinay.windyapp.flowfeature.data.FlowsRepository
import ru.gnusinay.windyapp.flowfeature.domain.CalculationUseCase

class FlowsScreenViewModel() : ViewModel() {

    companion object {

        const val DELAY_BETWEEN_SHOW_RESULT_ITEMS = 100L
    }

    var state by mutableStateOf(FlowsScreenState())
        private set

    private var calculationJob: Job? = null

    fun setFlowCount(value: String) {
        when {
            value.isEmpty() -> 0
            value.isDigitsOnly() -> value.toInt()
            else -> null
        }
            ?.let {
                state = state.copy(
                    flowsCount = it,
                )
            }
    }

    fun runCalculation(flowsCount: Int) {
        stopCalculation()
        resetState(flowsCount)

        val repository = FlowsRepository(flowsCount)
        val useCase = CalculationUseCase(repository)
        calculationJob = useCase()
            .onEach { updateCalculationResult(it) }
            .onEach { delay(DELAY_BETWEEN_SHOW_RESULT_ITEMS) }
            .launchIn(viewModelScope)
    }

    fun stopCalculation() {
        calculationJob?.cancel()
    }

    private fun updateCalculationResult(newValue: Int) {
        state = state.let { s ->
            s.copy(
                calculationResult = s.calculationResult +
                    ('\n'.takeIf { s.calculationResult.isNotEmpty() } ?: "") +
                    newValue,
            )
        }
    }

    private fun resetState(flowCount: Int) {
        state = FlowsScreenState(flowCount)
    }
}
