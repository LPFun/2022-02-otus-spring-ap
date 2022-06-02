package ru.lpfun.spring.homework02.services

import ru.lpfun.spring.homework02.common.interfaces.IdentificationService
import ru.lpfun.spring.homework02.common.interfaces.io.IOService
import ru.lpfun.spring.homework02.common.interfaces.io.InputService
import ru.lpfun.spring.homework02.common.model.Student

class IdentificationStudentService(
    private val ioService: IOService
) : IdentificationService<Student> {
    override fun identificate(): Student {
        ioService.print("Enter your name:")
        val name = askName(ioService)
        return Student(name.ifBlank { "Unknown" })
    }

    private fun askName(inputService: InputService): String {
        return inputService.getInput()
    }
}