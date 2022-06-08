package ru.lpfun.spring.homework02.services

import ru.lpfun.spring.homework02.common.interfaces.IdentificationStudentService
import ru.lpfun.spring.homework02.common.interfaces.io.IOService
import ru.lpfun.spring.homework02.common.model.Student

class IdentificationStudentServiceImpl(
    private val ioService: IOService
) : IdentificationStudentService {
    override fun identificate(): Student {
        ioService.print("Enter your name:")
        val name = ioService.getInput()
        return Student(name.ifBlank { "Unknown" })
    }
}