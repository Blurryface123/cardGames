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
        config.enableSimpleBroker("/queue/","/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/card-games")
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
                .withSockJS();
        registry.addEndpoint("/virus-game")
                .setAllowedOrigins("*")
                .withSockJS();
    }



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
