package ru.lpfun.spring.homework04.common.interfaces

import ru.lpfun.spring.homework04.common.model.Student

interface IdentificationStudentService {
    fun identificate(): Student
    fun identificate(name: String): Student
}