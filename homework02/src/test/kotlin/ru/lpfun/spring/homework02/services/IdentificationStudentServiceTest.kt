package ru.lpfun.spring.homework02.services

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework02.common.interfaces.IdentificationService
import ru.lpfun.spring.homework02.common.interfaces.io.IOService
import ru.lpfun.spring.homework02.common.model.Student
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
internal class IdentificationStudentServiceTest() {

    @MockkBean
    private lateinit var ioService: IOService

    private lateinit var identificationStudentService: IdentificationService<Student>

    @Test
    fun `Enter name test`() {
        val stubStudentName = "student name"
        identificationStudentService = IdentificationStudentService(ioService)
        every { ioService.getInput() } returns stubStudentName
        every { ioService.print(allAny()) } answers { println(args[0]) }

        val student = identificationStudentService.identificate()

        assertEquals(stubStudentName, student.name)

        verify {
            ioService.getInput()
            ioService.print(allAny())
        }
    }

    @Test
    fun `Enter empty name test`() {
        identificationStudentService = IdentificationStudentService(ioService)

        every { ioService.getInput() } returns ""
        every { ioService.print(allAny()) } answers { println(args[0]) }

        val identified = identificationStudentService.identificate()

        assertEquals("Unknown", identified.name)

        verify {
            ioService.getInput()
            ioService.print(allAny())
        }
    }
}