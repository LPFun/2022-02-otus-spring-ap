package ru.lpfun.spring.homework04.shell

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ru.lpfun.spring.homework04.common.interfaces.ExamService

@ShellComponent
class ExamStarterShell(
    private val examService: ExamService
) {
    @ShellMethod(value = "Start exam", key = ["ex", "start"])
    fun startExam() {
        examService.exam()
    }
}