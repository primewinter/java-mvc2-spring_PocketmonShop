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
		//setAllowedOrigins : Spring 5.2.3���� �����ϴ� method
		//(registry.addEndpoint("/stomp-chat")).setAllowedOrigins("*").withSockJS();
		(registry.addEndpoint("/stomp-chat")).withSockJS();
	}
	
	/*
	 * ������ interface�� ����� WebSocketMessageBrokerConfigurer�� �ٲ����. registerStompEndpoints���� ������ WebSocket ������ ���������� handshake�� ����� ����� endpoint�� �����Ѵ�. configureMessageBroker���� Application ���ο��� ����� path�� ������ �� �ִ�.

setApplicationDestinationPrefixes : client���� SEND ��û�� ó���Ѵ�.
Spring Reference������ /topic, /queue�� �ַ� �����ϴµ� ���⼭�� ���ظ� ���� ���� /publish�� �����Ͽ���.
/topic : �Ͻ������� 1:N ���ĸ� �ǹ��Ѵ�.
/queue : �Ͻ������� 1:1 ���ĸ� �ǹ��Ѵ�.
enableSimpleBroker : �ش� ��η� SimpleBroker�� ����Ѵ�. SimpleBroker�� �ش��ϴ� ��θ� SUBSCRIBE�ϴ� client���� �޽����� �����ϴ� ������ �۾��� �����Ѵ�.
enableStompBrokerRelay : SimpleBroker�� ��ɰ� �ܺ� message broker(RabbitMQ, ActiveMQ ��)�� �޽����� �����ϴ� ����� ������ �ִ�.
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
