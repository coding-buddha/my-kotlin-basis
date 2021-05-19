package edu.pasudo123.study.demo.schedulews

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.text.SimpleDateFormat
import java.util.*

@Controller
class WsTaskController {

    @MessageMapping("/ws-task")
    @SendTo("/topic/task-messages")
    fun task(message: String): String {
        val time = SimpleDateFormat("HH:mm:ss").format(Date())
        return "empty - $time"
    }

}