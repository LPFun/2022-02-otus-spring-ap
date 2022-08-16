package ru.lpfun.spring.homework04.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "app.settings")
data class AppSettings(
    val lang: String
)