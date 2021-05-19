package edu.pasudo123.study.demo.ws

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
     *
     */
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic")
        config.setApplicationDestinationPrefixes("/app")
    }

    /**
     * stomp single 프로토콜을 사용토록 한다.
     * @MessageMapping 애노테이션에 매핑되는 EndPoint 를 설정한다.
     */
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/chat")
        registry.addEndpoint("/chat").withSockJS()
    }
}