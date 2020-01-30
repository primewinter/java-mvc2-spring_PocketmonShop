package com.model2.mvc.common.NOTUSED;

import java.util.List;

import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// TODO Auto-generated method stub
		registry.setApplicationDestinationPrefixes("/publish");
        registry.enableSimpleBroker("/subscribe");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//setAllowedOrigins : Spring 5.2.3에서 지원하는 method
		//(registry.addEndpoint("/stomp-chat")).setAllowedOrigins("*").withSockJS();
		(registry.addEndpoint("/stomp-chat")).withSockJS();
	}
	
	/*
	 * 구현할 interface의 대상이 WebSocketMessageBrokerConfigurer로 바뀌었다. registerStompEndpoints에서 기존의 WebSocket 설정과 마찬가지로 handshake와 통신을 담당할 endpoint를 지정한다. configureMessageBroker에서 Application 내부에서 사용할 path를 지정할 수 있다.

setApplicationDestinationPrefixes : client에서 SEND 요청을 처리한다.
Spring Reference에서는 /topic, /queue가 주로 등장하는데 여기서는 이해를 돕기 위해 /publish로 지정하였다.
/topic : 암시적으로 1:N 전파를 의미한다.
/queue : 암시적으로 1:1 전파를 의미한다.
enableSimpleBroker : 해당 경로로 SimpleBroker를 등록한다. SimpleBroker는 해당하는 경로를 SUBSCRIBE하는 client에게 메시지를 전달하는 간단한 작업을 수행한다.
enableStompBrokerRelay : SimpleBroker의 기능과 외부 message broker(RabbitMQ, ActiveMQ 등)에 메시지를 전달하는 기능을 가지고 있다.
	 * 
	 */

	@Override
	public void configureClientInboundChannel(ChannelRegistration arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean configureMessageConverters(List<MessageConverter> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration arg0) {
		// TODO Auto-generated method stub
		
	}

}
