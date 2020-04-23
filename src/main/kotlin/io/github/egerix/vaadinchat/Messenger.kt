package io.github.egerix.vaadinchat


import java.util.*

object Messenger : Observable() {

    fun sendMessage(text: String) {
        setChanged()
        notifyObservers(text)
    }
}