package ru.gnusinay.windyapp.flowfeature.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.runningReduce
import ru.gnusinay.windyapp.flowfeature.data.FlowsRepository

class CalculationUseCase(
    private val flowsRepository: FlowsRepository
) {

    operator fun invoke(): Flow<Int> {
        return flowsRepository.flows.merge()
            .runningReduce { accumulator, value -> accumulator + value }
    }
}