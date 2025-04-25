package com.example.applet.im;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.example.applet.services.MessageService;

@Component
public class WSMessageHandler extends TextWebSocketHandler {
    private Map<Long, WebSocketSession> sessions = Collections.synchronizedMap(new HashMap<>());
    private final MessageService messageService;

    public WSMessageHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long userId = (Long) session.getAttributes().get("userId");
        if (userId != null) {
            sessions.put(userId, session);
        }
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Long senderId = (Long) session.getAttributes().get("userId");
        String payload = message.getPayload();
        String[] parts = payload.split(":", 2);
        Long receiverId = Long.parseLong(parts[0]);
        String content = parts[1];

        messageService.send(senderId, receiverId, content);

        WebSocketSession receiverSession = sessions.get(receiverId);
        if (receiverSession != null && receiverSession.isOpen()) {
            receiverSession.sendMessage(new TextMessage(content));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long userId = (Long) session.getAttributes().get("userId");
        sessions.remove(userId);
    }
}
