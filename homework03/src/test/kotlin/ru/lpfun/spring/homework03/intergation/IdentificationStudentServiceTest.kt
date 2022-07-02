package ru.lpfun.spring.homework03.intergation

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.MessageSource
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.services.IdentificationStudentServiceImpl
import java.util.*

@SpringBootTest
@ExtendWith(SpringExtension::class)
internal class IdentificationStudentServiceTest() {

    @MockK
    private lateinit var ioService: IOService

    @Autowired
    private lateinit var messageSource: MessageSource

    @Test
    fun `enter name test ru locale`() {
        Locale.setDefault(Locale("ru", "RU"))
        val outPutMsg = messageSource.getMessage("identification.enter-name", null, Locale.getDefault())

        val stubStudentName = "student name"
        val identificationStudentService = IdentificationStudentServiceImpl(ioService, messageSource)
        val capOutput = slot<String>()

        every { ioService.getInput() } returns stubStudentName
        every { ioService.print(capture(capOutput)) } answers { println(args.first()) }

        val student = identificationStudentService.identificate()

        assertEquals(outPutMsg, capOutput.captured)
        assertEquals(stubStudentName, student.name)

        verify {
            ioService.getInput()
            ioService.print(any())
        }
    }

    @Test
    fun `enter empty name test en locale`() {
        Locale.setDefault(Locale.ENGLISH)
        val outPutMsg = messageSource.getMessage("identification.enter-name", null, Locale.getDefault())
        val capOutput = slot<String>()

        val identificationStudentService = IdentificationStudentServiceImpl(ioService, messageSource)

        every { ioService.getInput() } returns ""
        every { ioService.print(capture(capOutput)) } answers { println(args.first()) }

        val identified = identificationStudentService.identificate()

        assertEquals(outPutMsg, capOutput.captured)
        assertEquals("Unknown", identified.name)

        verify {
            ioService.getInput()
            ioService.print(allAny())
        }
    }
}