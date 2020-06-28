package com.games.cardGames.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class webSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //registry.addEndpoint("/card-games")
                //.setAllowedOrigins("mydomain.com")
                /*.setHandshakeHandler(new DefaultHandshakeHandler(){
                    public boolean beforeHandShake(ServerHttpRequest request, ServerHttpResponse response,
                                                WebSocketHandler wshandler, Map attributes) throws Exception{
                        if(request instanceof ServletServerHttpRequest){
                            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
                            HttpSession session = serverRequest.getServletRequest().getSession();
                            attributes.put("sessionId",session.getId());
                            System.out.println(attributes.get("sessionId"));
                        }
                        return true;

                    }
                })*/
                //.withSockJS();
        registry.addEndpoint("/virus-game")
                .setHandshakeHandler(new DefaultHandshakeHandler() {

                    public boolean beforeHandshake(ServerHttpRequest request,ServerHttpResponse response,
                            WebSocketHandler wsHandler, Map attributes) throws Exception {

                        if (request instanceof ServletServerHttpRequest) {
                            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;

                            HttpSession session = servletRequest.getServletRequest().getSession();
                            attributes.put("sessionId", session.getId());
                        }
                        return true;
                    }})
                .setAllowedOrigins("http://127.0.0.1:5500")
                .withSockJS();
    }


   /* @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/myHandler").setAllowedOrigins("http://127.0.0.1:5500/board/board.html");
    }
ean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }*/
    /*//ENDPOINT
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/chat");
    }*/
}
