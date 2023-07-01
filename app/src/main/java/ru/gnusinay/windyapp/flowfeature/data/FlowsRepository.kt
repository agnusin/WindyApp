package ru.gnusinay.windyapp.flowfeature.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlowsRepository(flowCount: Int) {

    companion object {
        const val DELAY_DURATION = 100L
    }

    val flows: List<Flow<Int>> = List(flowCount) { index ->
        val delayDuration = (index + 1) * DELAY_DURATION
        flow {
            delay(delayDuration)
            emit(index + 1)
        }
    }
}
