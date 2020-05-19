package com.games.cardGames.gameController;

import com.games.cardGames.models.Greeting;
import com.games.cardGames.models.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class virusGame {
    @MessageMapping("/hello")//DONDE SE RECIBE LO QUE ENVIA EL CLIENTE
    @SendTo("/topic/greetings")//SE LE ENVIA AL CLIENTE SUBSCRITO
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/users")
    public int listUsersId(List<Integer> usersId){
        for (Integer user: usersId){
            System.out.println(user.toString());
            return user;
        }
        return 0;
    }



    /*private final SimpMessagingTemplate template;

    @Autowired
    public virusGame(SimpMessagingTemplate template) {
        this.template = template;
    }

    //AQUI VA LA LOGICA DEL JUEGO
    @MessageMapping("send/message")
    public void onReceiveMessage(String message){
        this.template.convertAndSend("/chat","Mensaje: "+message);
    }*/
}
