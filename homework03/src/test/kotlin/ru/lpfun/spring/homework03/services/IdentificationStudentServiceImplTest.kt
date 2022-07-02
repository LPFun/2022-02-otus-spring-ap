package ru.lpfun.spring.homework03.services

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.MessageSource
import ru.lpfun.spring.homework03.common.interfaces.IdentificationStudentService
import ru.lpfun.spring.homework03.common.interfaces.io.IOService

@ExtendWith(MockKExtension::class)
internal class IdentificationStudentServiceImplTest() {

    @MockK
    private lateinit var ioService: IOService

    @MockK
    private lateinit var messageSource: MessageSource

    private lateinit var identificationStudentService: IdentificationStudentService

    @Test
    fun `Enter name test`() {
        val stubStudentName = "student name"
        identificationStudentService = IdentificationStudentServiceImpl(ioService, messageSource)

        every { ioService.getInput() } returns stubStudentName
        every { ioService.print(any()) } answers { println(args[0]) }
        every { messageSource.getMessage(any(), any(), any()) } returns "mock msg"

        val student = identificationStudentService.identificate()

        assertEquals(stubStudentName, student.name)

        verify {
            messageSource.getMessage(any(), any(), any())
            ioService.getInput()
            ioService.print(any())
        }
    }

    @Test
    fun `Enter empty name test`() {
        identificationStudentService = IdentificationStudentServiceImpl(ioService, messageSource)

        every { ioService.getInput() } returns ""
        every { ioService.print(allAny()) } answers { println(args[0]) }
        every { messageSource.getMessage(any(), any(), any()) } returns "Unknown"

        val identified = identificationStudentService.identificate()

        assertEquals("Unknown", identified.name)

        verify {
            messageSource.getMessage(any(), any(), any())
            ioService.getInput()
            ioService.print(allAny())
        }
    }
}