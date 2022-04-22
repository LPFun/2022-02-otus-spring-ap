package ru.lpfun.spring.homework01.parser

import com.opencsv.bean.CsvBindByName
import ru.lpfun.spring.homework01.common.model.Answer
import ru.lpfun.spring.homework01.common.model.Question

data class QuestionCsv(
    @CsvBindByName(column = "number")
    val number: String? = null,
    @CsvBindByName(column = "question")
    val question: String? = null,
    @CsvBindByName(column = "answer variants")
    val answerVariants: String? = null,
    @CsvBindByName(column = "correct answers")
    val correctAnswers: String? = null,
) {
    fun toModel() = Question(
        id = number?.trim() ?: "",
        question = question?.trim() ?: "",
        answers = answerVariants?.toAnswers() ?: emptyList(),
        correctAnswers = correctAnswers?.toAnswers() ?: emptyList()
    )

    private fun String.toAnswers(): List<Answer> {
        return trim().split(" ").mapIndexed { i, a ->
            Answer(
                id = i.toString(),
                answer = a
            )
        }
    }
}
