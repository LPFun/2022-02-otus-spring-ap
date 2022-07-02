package ru.lpfun.spring.homework03.services

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.IdentificationStudentService
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.common.model.Student
import java.util.*

@Service
class IdentificationStudentServiceImpl(
    private val ioService: IOService,
    private val messageSource: MessageSource,
) : IdentificationStudentService {
    override fun identificate(): Student {
        ioService.print(messageSource.getMessage("identification.enter-name", null, Locale.getDefault()))
        val name = ioService.getInput()
        return Student(name.ifBlank { messageSource.getMessage("identification.unknown", null, Locale.getDefault()) })
    }
}