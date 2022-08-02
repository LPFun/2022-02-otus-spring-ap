package ru.lpfun.spring.homework03.common.interfaces

import ru.lpfun.spring.homework03.common.model.Student

interface IdentificationStudentService {
    fun identificate(): Student
}