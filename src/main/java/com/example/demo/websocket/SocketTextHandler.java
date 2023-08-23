package com.example.demo.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketTextHandler extends TextWebSocketHandler {
	private Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
	
	public static WebSocketSession id;
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		sessions.add(session);
		
		// 클라이언트에게 메시지를 보내는 예시 코드
        //String responseMessage = "Hello, client!";
        
		System.out.print("세션 아이디는 " +session.getId());
        id = session;
        
        //extMessage textMessage = new TextMessage(responseMessage);
        
        //session.sendMessage(textMessage);
        
        System.out.print(id.toString());


	}
	
	
	public void sendmessage(WebSocketSession session, String message) {
	        try {
	        	
	            session.sendMessage(new TextMessage(message));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	    }
	

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
	}
	

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		sessions.remove(session);
	}
	

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}

}
