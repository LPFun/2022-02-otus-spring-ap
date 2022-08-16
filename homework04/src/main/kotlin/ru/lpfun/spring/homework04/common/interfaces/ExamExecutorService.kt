package ru.lpfun.spring.homework04.common.interfaces

import ru.lpfun.spring.homework04.common.model.ExamResult

interface ExamExecutorService {
    fun executeExam(): ExamResult
}