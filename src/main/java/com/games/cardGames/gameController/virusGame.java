package com.games.cardGames.gameController;

import com.games.cardGames.models.Card;
import com.games.cardGames.models.Coordinate;
import com.games.cardGames.models.Greeting;
import com.games.cardGames.models.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.util.HtmlUtils;



@Controller
public class virusGame {
    private static final Logger LOGGER = LoggerFactory.getLogger(virusGame.class);

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

        return  new Coordinate(coords.getId(),coords.getY(),coords.getX());
    }

    @MessageMapping("/createCard")
    @SendTo("/topic/newCard")
    public Card newCard (Card card){


        String cardImg = "<img class=\"card-img\" src=\"./img/carta-virus.jpg\" alt=\"Card image\">" +
                "<div class=\"card-image-overlay\"></div>\"";
        return  new Card(card.getId(),card.getCardValue(),cardImg);
    }

    @MessageMapping("/setImg")
    @SendTo("/topic/newImg")
    public Card newImg (Card card, HttpSessionHandshakeInterceptor session){
        System.out.println(session.getAttributeNames().toString());

        return  new Card(card.getId(),card.getCardValue(),card.getCardImg());
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
