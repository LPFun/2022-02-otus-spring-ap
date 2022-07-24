package ru.lpfun.spring.homework03.intergation

import com.ninjasquad.springmockk.MockkBean
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import ru.lpfun.spring.homework03.common.interfaces.IdentificationStudentService
import ru.lpfun.spring.homework03.common.interfaces.MsgPrinter
import ru.lpfun.spring.homework03.common.interfaces.MsgProvider
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.services.IdentificationStudentServiceImpl

@SpringBootTest(classes = [IdentificationStudentServiceImpl::class])
@ExtendWith(SpringExtension::class)
internal class IdentificationStudentServiceTest() {

    @MockkBean
    private lateinit var ioService: IOService

    @MockkBean
    private lateinit var msgPrinter: MsgPrinter

    @MockkBean
    private lateinit var msgProvider: MsgProvider

    @Autowired
    private lateinit var identificationStudentService: IdentificationStudentService

    @Test
    fun `enter name test`() {
        val outPutMsg = "identification.enter-name"

        val stubStudentName = "student name"
        val capOutput = slot<String>()

        every { msgPrinter.printlnMsg(capture(capOutput)) } just Runs
        every { ioService.getInput() } returns stubStudentName

        val student = identificationStudentService.identificate()

        assertEquals(outPutMsg, capOutput.captured)
        assertEquals(stubStudentName, student.name)

        verify {
            ioService.getInput()
            msgPrinter.printlnMsg(any())
        }
    }

    @Test
    fun `enter empty name test`() {
        val outPutMsg = "identification.enter-name"
        val capOutput = slot<String>()

        every { msgPrinter.printlnMsg(capture(capOutput)) } just runs
        every { ioService.getInput() } returns ""
        every { msgProvider.getMsg("identification.unknown") } returns "Unknown"


        val identified = identificationStudentService.identificate()

        assertEquals(outPutMsg, capOutput.captured)
        assertEquals("Unknown", identified.name)

        verify {
            ioService.getInput()
            msgPrinter.printlnMsg(any())
            msgProvider.getMsg(any())
        }
    }
}