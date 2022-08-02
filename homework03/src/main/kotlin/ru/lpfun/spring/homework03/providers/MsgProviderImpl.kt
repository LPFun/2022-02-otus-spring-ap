package ru.lpfun.spring.homework03.providers

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import ru.lpfun.spring.homework03.common.interfaces.MsgProvider
import java.util.*

@Component
class MsgProviderImpl(
    private val messageSource: MessageSource,
    @Value("\${app.settings.lang}") private val lang: String,
) : MsgProvider {
    private val locale: Locale = Locale(lang)

    override fun getMsg(code: String, args: Array<out Any>?): String {
        return messageSource.getMessage(code, args, locale)
    }
}