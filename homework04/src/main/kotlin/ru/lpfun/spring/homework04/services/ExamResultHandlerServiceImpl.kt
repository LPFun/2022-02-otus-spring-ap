package ru.lpfun.spring.homework04.services

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework04.common.interfaces.ExamResultHandlerService
import ru.lpfun.spring.homework04.common.interfaces.MsgPrinter
import ru.lpfun.spring.homework04.common.interfaces.MsgProvider
import ru.lpfun.spring.homework04.common.model.ExamResult
import ru.lpfun.spring.homework04.common.model.Student

@Service
class ExamResultHandlerServiceImpl(
    private val msgPrinter: MsgPrinter,
    private val msgProvider: MsgProvider
) : ExamResultHandlerService {
    override fun handleExamResult(student: Student, examResult: ExamResult) {
        msgPrinter.run {
            printlnMsg("result.exam-result")
            val passedExamText = msgProvider.getMsg(
                if (examResult.isExamPassed) "result.passed" else "result.not-passed"
            )
            printlnMsg("result.exam", arrayOf(passedExamText))
            printlnMsg(
                "result.detailed-result",
                arrayOf(student.name, examResult.trueAnswersCount, examResult.questionsCount)
            )
        }
    }
}