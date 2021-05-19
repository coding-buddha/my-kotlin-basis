package edu.pasudo123.study.demo.schedulews

import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*


@Component
class WsTask(
    private val messageSendingOperations: SimpMessageSendingOperations
){

    private val log = LoggerFactory.getLogger(WsTask::class.java)

    @Scheduled(fixedRate = 3000L)
    fun sendToClientTask() {
        val uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 10)
        val time = SimpleDateFormat("HH:mm:ss").format(Date())
        val message = "$time - $uuid"
        log.info("schedule-message : {}", message)
        messageSendingOperations.convertAndSend("/topic/task-messages", message)
    }
}