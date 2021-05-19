package edu.pasudo123.study.demo.ws

import edu.pasudo123.study.demo.helper.toJson
import edu.pasudo123.study.demo.ws.model.CustomMessage
import edu.pasudo123.study.demo.ws.model.OutputMessage
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.text.SimpleDateFormat
import java.util.*


@Controller
class ChatController {

    private val log = LoggerFactory.getLogger(ChatController::class.java)

    /**
     * 클라이언트의 메시지를 /chat 으로 받고,
     * /topic/messages 를 구독한 클라이언트 subscriber 에게 메세지를 전송한다.
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    fun chat(message: CustomMessage): OutputMessage {
        log.info("==> {}", message.toJson())
        val time = SimpleDateFormat("HH:mm:ss").format(Date())
        return OutputMessage(message.from, message.text, time)
    }
}