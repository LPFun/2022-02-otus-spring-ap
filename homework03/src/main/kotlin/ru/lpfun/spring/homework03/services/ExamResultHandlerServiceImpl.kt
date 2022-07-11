package ru.lpfun.spring.homework03.services

import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.ExamResultHandlerService
import ru.lpfun.spring.homework03.common.interfaces.MsgProvider
import ru.lpfun.spring.homework03.common.model.ExamResult
import ru.lpfun.spring.homework03.common.model.Student

@Service
class ExamResultHandlerServiceImpl(
    private val msgProvider: MsgProvider
) : ExamResultHandlerService {
    override fun handleExamResult(student: Student, examResult: ExamResult) {
        msgProvider.run {
            printlnMsg("result.exam-result")
            val passedExamText = getMsg(
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