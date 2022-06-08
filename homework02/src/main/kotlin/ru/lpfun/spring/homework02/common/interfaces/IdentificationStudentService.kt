package ru.lpfun.spring.homework02.common.interfaces

import ru.lpfun.spring.homework02.common.model.Student

interface IdentificationStudentService {
    fun identificate(): Student
}