package com.games.cardGames.gameController;

import com.games.cardGames.models.Coordinate;
import com.games.cardGames.models.Greeting;
import com.games.cardGames.models.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Controller
public class virusGame {
    @MessageMapping("/hello")//DONDE SE RECIBE LO QUE ENVIA EL CLIENTE
    @SendTo("/topic/greetings")//SE LE ENVIA AL CLIENTE SUBSCRITO
    @RequestMapping(path = "/test1")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/prueba")
    @SendTo("/topic/conexion")
    @RequestMapping(path = "/board")
    public Coordinate positions (Coordinate coords){

        return new Coordinate(coords.getY(),coords.getX());
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
