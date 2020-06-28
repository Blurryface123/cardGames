package com.games.cardGames.gameController;

import com.games.cardGames.gameService.VirusService;
import com.games.cardGames.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.security.Principal;
import java.util.ArrayList;


@Controller
public class VirusGame {
    private static final Logger LOGGER = LoggerFactory.getLogger(VirusGame.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private static VirusService newVirusService = new VirusService();


    public static final ArrayList<String> SHUFFLE_DECK;

    static {
        SHUFFLE_DECK = newVirusService.shuffle();
    }



    @MessageMapping("/pideCarta")//DONDE SE RECIBE LO QUE ENVIA EL CLIENTE
    @SendToUser("/queue/dealCard")
    public Card darCarta(User userId, Principal principal) throws Exception{
        //hacer un string final con esto
        /*String cardImg = "<img class=\"card-img\" src=\"\" alt=\"Card image\">" +
                "<div class=\"card-image-overlay\"></div>";*/


        String cardValue = newVirusService.dealSingleCard(SHUFFLE_DECK);
        System.out.println(SHUFFLE_DECK.size());
        System.out.println(SHUFFLE_DECK);
        Card cardToDeal = new Card("card-"+cardValue,cardValue,null);
        SHUFFLE_DECK.remove(SHUFFLE_DECK.get(0));
        //messagingTemplate.convertAndSendToUser(userId.getId(),"/queue/dealCard",new Card("card-"+cardValue,cardValue,cardImg));

        return cardToDeal;

    }

    @MessageMapping("/playCard")
    @SendTo("/topic/sendPlayedCard")
    public Card cardToPlay (Card card){

        return  card;
    }

    @MessageMapping("/prueba")
    @SendTo("/topic/conexion")
    @RequestMapping(path = "/board")
    public Coordinate positions (Coordinate coords){

        return  new Coordinate(coords.getId(),coords.getY(),coords.getX());
    }



    @MessageMapping("/setImg")
    @SendTo("/topic/newImg")
    public Card newImg (Card card, HttpSessionHandshakeInterceptor session){
        System.out.println(session.getAttributeNames().toString());

        return  new Card(card.getId(),card.getCardValue(),card.getCoords());
    }



    /*private final SimpMessagingTemplate template;

    @Autowired
    public VirusGame(SimpMessagingTemplate template) {
        this.template = template;
    }

    //AQUI VA LA LOGICA DEL JUEGO
    @MessageMapping("send/message")
    public void onReceiveMessage(String message){
        this.template.convertAndSend("/chat","Mensaje: "+message);
    }*/
}
