package ru.lpfun.spring.homework02.common.model

data class Answer(
    val id: String = "",
    val answer: String = "",
) {
    companion object{
        val NONE = Answer()
    }
}
