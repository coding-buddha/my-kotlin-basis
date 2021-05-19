package edu.pasudo123.study.demo

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

/**
 * 웹소켓 메시지를 핸들링 하고, 메시지 브로커를 지원한다.
 */
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfiguration : WebSocketMessageBrokerConfigurer{

    /**
     * config.enableSimpleBroker 로 설정한 path 로 클라이언트에게 메시지를 전송한다.
     */
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        // 클리이언트가 "/topic" prefix 를 기준으로 서버에게 데이터를 받겠다는 의미
        config.enableSimpleBroker("/topic")

        // 클라이언트는 "/app" prefix 를 기준으로 서버에게 데이터를 보내겠다는 의미
        config.setApplicationDestinationPrefixes("/app")
    }

    /**
     * stomp single 프로토콜을 사용하기 위한 url
     * @MessageMapping 애노테이션에 매핑되는 EndPoint 를 설정한다.
     *
     * [1] 클라이언트는 /app/chat 으로 데이터를 전송하면, 서버는 @MessageMapping 에 매핑된 /chat 으로 데이터를 받는다.
     * [2] 클라이언트는 /app/ws-task 으로 데이터를 전송하면, 서버는 @MessageMapping 에 매핑된 /chat 으로 데이터를 받는다.
     */
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/chat")
        registry.addEndpoint("/chat").withSockJS()

        registry.addEndpoint("/ws-task")
        registry.addEndpoint("/ws-task").withSockJS()
    }
}