package ru.lpfun.spring.homework03.common.model

data class Answer(
    val id: String = "",
    val answer: String = "",
) {
    companion object {
        val NONE = Answer()
    }
}
