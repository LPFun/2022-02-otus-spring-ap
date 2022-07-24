package ru.lpfun.spring.homework03.services

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.IdentificationStudentService
import ru.lpfun.spring.homework03.common.interfaces.MsgPrinter
import ru.lpfun.spring.homework03.common.interfaces.MsgProvider
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.common.model.Student

@Service
class IdentificationStudentServiceImpl(
    private val ioService: IOService,
    private val msgPrinter: MsgPrinter,
    private val msgProvider: MsgProvider
) : IdentificationStudentService {
    override fun identificate(): Student {
        msgPrinter.printlnMsg("identification.enter-name")
        val name = ioService.getInput()
        return Student(name.ifBlank { msgProvider.getMsg("identification.unknown") })
    }
}