package io.github.egerix.vaadinchat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VaadinChatApplication

fun main(args: Array<String>) {
	runApplication<VaadinChatApplication>(*args)
}
