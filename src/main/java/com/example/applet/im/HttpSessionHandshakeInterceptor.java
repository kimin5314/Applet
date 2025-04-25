package com.example.applet.im;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

public class HttpSessionHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof org.springframework.http.server.ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (org.springframework.http.server.ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            String userId = servletRequest.getServletRequest().getParameter("userId");
            if (userId != null) {
                attributes.put("userId", Long.parseLong(userId));
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
