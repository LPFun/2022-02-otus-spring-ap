package ru.lpfun.spring.homework04.services

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import ru.lpfun.spring.homework04.common.interfaces.IdentificationStudentService
import ru.lpfun.spring.homework04.common.interfaces.MsgPrinter
import ru.lpfun.spring.homework04.common.interfaces.MsgProvider
import ru.lpfun.spring.homework04.common.interfaces.io.IOService

@ExtendWith(MockKExtension::class)
internal class IdentificationStudentServiceImplTest() {

    @MockK
    private lateinit var ioService: IOService

    @MockK
    private lateinit var msgPrinter: MsgPrinter

    @MockK
    private lateinit var msgProvider: MsgProvider

    private lateinit var identificationStudentService: IdentificationStudentService

    @Test
    fun `Enter name test`() {
        val stubStudentName = "student name"
        identificationStudentService = IdentificationStudentServiceImpl(ioService, msgPrinter, msgProvider)

        every { ioService.getInput() } returns stubStudentName
        every { msgPrinter.printlnMsg(any()) } just Runs

        val student = identificationStudentService.identificate()

        assertEquals(stubStudentName, student.name)

        verify {
            ioService.getInput()
            msgPrinter.printlnMsg(any())
        }
    }

    @Test
    fun `Enter empty name test`() {
        identificationStudentService = IdentificationStudentServiceImpl(ioService, msgPrinter, msgProvider)

        every { ioService.getInput() } returns ""
        every { msgPrinter.printlnMsg("identification.enter-name") } just Runs
        every { msgProvider.getMsg("identification.unknown") } returns "Unknown"

        val identified = identificationStudentService.identificate()

        assertEquals("Unknown", identified.name)

        verify {
            msgPrinter.printlnMsg("identification.enter-name")
            ioService.getInput()
        }
    }
}