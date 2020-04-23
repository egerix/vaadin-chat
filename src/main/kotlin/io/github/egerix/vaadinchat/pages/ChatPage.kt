package io.github.egerix.vaadinchat.pages


import com.github.javafaker.Faker
import com.github.mvysny.karibudsl.v10.button
import com.github.mvysny.karibudsl.v10.content
import com.github.mvysny.karibudsl.v10.horizontalLayout
import com.github.mvysny.karibudsl.v10.isExpand
import com.github.mvysny.karibudsl.v10.onLeftClick
import com.github.mvysny.karibudsl.v10.textArea
import com.github.mvysny.karibudsl.v10.textField
import com.github.mvysny.karibudsl.v10.verticalLayout
import com.vaadin.flow.component.AttachEvent
import com.vaadin.flow.component.DetachEvent
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.component.page.Push
import com.vaadin.flow.component.textfield.TextArea
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import io.github.egerix.vaadinchat.Messenger
import java.util.*


@Route("")
@Push
class ChatPage : VerticalLayout(), Observer {
    private lateinit var messageTxb: TextField
    private lateinit var chatTextArea: TextArea
    private val userName = Faker.instance().name().name()

    override fun onAttach(attachEvent: AttachEvent) {
        Messenger.addObserver(this)
    }

    override fun onDetach(detachEvent: DetachEvent?) {
        Messenger.deleteObserver(this)
    }

    init {
        height = "80vh"
        content {
            align(center, top)
        }
        verticalLayout {
            width = "60vw"
            isExpand = true
            content {
                align(stretch, top)
            }
            chatTextArea = textArea {
                isReadOnly = true
                height = "60vh"
                maxHeight = height
            }
            horizontalLayout {
                messageTxb = textField {
                    isExpand = true
                }
                button("Send") {
                    addClickShortcut(Key.ENTER)
                    onLeftClick { sendMessage() }
                }
            }
        }
    }

    private fun sendMessage() {
        val messageText = messageTxb.value
        if (messageText.isNotEmpty()) {
            Messenger.sendMessage("[$userName]: $messageText")
            messageTxb.clear()
        }
    }

    override fun update(o: Observable?, arg: Any?) {
        arg?.let {
            chatTextArea.ui.get().access {
                chatTextArea.value += "$arg\n"
            }
        }
    }
}