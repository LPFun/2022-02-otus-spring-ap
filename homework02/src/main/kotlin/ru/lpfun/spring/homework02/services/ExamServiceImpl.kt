package ru.lpfun.spring.homework02.services

import ru.lpfun.spring.homework02.common.interfaces.ExamService
import ru.lpfun.spring.homework02.common.interfaces.QuestionDao
import ru.lpfun.spring.homework02.common.model.Answer
import ru.lpfun.spring.homework02.common.model.Question
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.PrintStream

interface InputService{
    fun getInput(): String
}

interface OutputService{
    fun print(str: String)
    fun println(str: String)
}

interface IOService: InputService, OutputService

class IOServiceImpl(
    private val `in`: InputStream,
    private val out: PrintStream,
): IOService{
    override fun getInput(): String {
        return BufferedReader(InputStreamReader(`in`)).readLine()
    }

    override fun print(str: String) {
        out.print(str)
    }

    override fun println(str: String) {
        out.println(str)
    }

}

data class Student(
    var name: String,
    var numOfPoints: Int,
)

class ExamServiceImpl(
    private val questionDao: QuestionDao,
    private val ioService: IOService,
) : ExamService {
    override fun exam() {
        // спросить имя
        ioService.print("Enter your name:")
        val name = askName(ioService)
        val student = Student(name, 0)
        // задать вопросы
        val questions = questionDao.getQuestions()
        ioService.println("Enter correct answer id")
        questions.forEach { q ->
            printQuestion(q, ioService)
            val answerId = ioService.getInput()
            val answer = q.answers.find { it.id == answerId} ?: Answer.NONE
            val isCorrect = isCorrectAnswer(answer, q.correctAnswers)
            if(isCorrect){
                student.apply {
                    numOfPoints += 1
                }
            }
        }
        // вывести результат
        printResult(ioService, student)
    }

    private fun printQuestion(question: Question, outputService: OutputService){
        outputService.print("${question.id}) ${question.question}")
        outputService.print(System.lineSeparator())
        question.answers.forEachIndexed { i, a ->
            outputService.print(" ${i})${a.answer}")
        }
        outputService.print(System.lineSeparator())
    }

    private fun printResult(outputService: OutputService, student: Student) {
        outputService.println("Exam result:")
        outputService.println("${student.name} ${student.numOfPoints}")
    }

    private fun isCorrectAnswer(answer: Answer, correctAnswers: List<Answer>): Boolean {
        return correctAnswers.any { it.answer == answer.answer }
    }

    private fun askName(inputService: InputService): String{
        return inputService.getInput()
    }

}