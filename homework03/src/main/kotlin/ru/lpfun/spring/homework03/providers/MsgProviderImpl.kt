package ru.lpfun.spring.homework03.providers

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import ru.lpfun.spring.homework03.common.interfaces.MsgProvider
import ru.lpfun.spring.homework03.common.interfaces.io.IOService
import ru.lpfun.spring.homework03.config.ExamProps
import java.util.*

@Service
class MsgProviderImpl(
    private val ioService: IOService,
    private val messageSource: MessageSource,
    private val examProps: ExamProps
) : MsgProvider {
    private val locale = Locale(examProps.lang)

    override fun printMsg(code: String, args: Array<out Any>?) {
        ioService.print(getMsg(code, args))
    }

    override fun printlnMsg(code: String, args: Array<out Any>?) {
        ioService.println(getMsg(code, args))
    }

    override fun getMsg(code: String, args: Array<out Any>?): String {
        return messageSource.getMessage(code, args, locale)
    }
}