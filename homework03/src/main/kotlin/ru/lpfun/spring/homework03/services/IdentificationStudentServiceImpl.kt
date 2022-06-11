package ru.lpfun.spring.homework03.services

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.IdentificationStudentService
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.common.model.Student

@Service
class IdentificationStudentServiceImpl(
    private val ioService: IOService
) : IdentificationStudentService {
    override fun identificate(): Student {
        ioService.print("Enter your name:")
        val name = ioService.getInput()
        return Student(name.ifBlank { "Unknown" })
    }
}