package ru.lpfun.spring.homework04.providers

import org.springframework.context.MessageSource
import org.springframework.stereotype.Component
import ru.lpfun.spring.homework04.common.interfaces.MsgProvider
import ru.lpfun.spring.homework04.config.AppSettings
import java.util.*

@Component
class MsgProviderImpl(
    private val messageSource: MessageSource,
    private val appSettings: AppSettings
) : MsgProvider {
    private val locale: Locale = Locale(appSettings.lang)

    override fun getMsg(code: String, args: Array<out Any>?): String {
        return messageSource.getMessage(code, args, locale)
    }
}