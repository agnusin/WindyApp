package ru.gnusinay.windyapp

import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import ru.gnusinay.windyapp.flowfeature.data.FlowsRepository
import ru.gnusinay.windyapp.flowfeature.domain.CalculationUseCase

class CalculationUseCaseTest {

    @Test
    fun testWith2Flows() = runBlocking {
        val repository = FlowsRepository(2)
        val useCase = CalculationUseCase(repository)

        val resultList = mutableListOf<Int>()
        useCase().toCollection(resultList)

        val referenceList = listOf(1, 3)
        assertEquals(referenceList, resultList)
    }

    @Test
    fun testWith3Flows() = runBlocking {
        val repository = FlowsRepository(3)
        val useCase = CalculationUseCase(repository)

        val resultList = mutableListOf<Int>()
        useCase().toCollection(resultList)

        val referenceList = listOf(1, 3, 6)
        assertEquals(referenceList, resultList)
    }

    @Test
    fun testWith7Flows() = runBlocking {
        val repository = FlowsRepository(7)
        val useCase = CalculationUseCase(repository)

        val resultList = mutableListOf<Int>()
        useCase().toCollection(resultList)

        val referenceList = listOf(1, 3, 6, 10, 15, 21, 28)
        assertEquals(referenceList, resultList)
    }
}
