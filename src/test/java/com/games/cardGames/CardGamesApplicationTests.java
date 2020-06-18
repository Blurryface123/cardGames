package com.games.cardGames;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class CardGamesApplicationTests {
	List<String> organo = Arrays.asList("corazon", "estomago", "cerebro", "hueso");
	List<String> virus = Arrays.asList("v-rojo", "v-amarillo", "v-verde", "v-azul");
	List<String> medicina = Arrays.asList("m-rojo", "m-amarillo", "m-verde", "m-azul");
	List<String> tratamiento = Arrays.asList("transplante", "ladron", "contagio", "guante", "error-medico");
	List<String> comodin = Arrays.asList("organo-comodin", "virus-comodin", "medicina-comodin",
			"medicina-comodin", "medicina-comodin", "medicina-comodin");



	@Test
	void contextLoads() {
	}

	ArrayList<String> shuffle(){

		ArrayList<String> deck = new ArrayList<>();
		ArrayList<String> listaOrgano = new ArrayList<>();
		ArrayList<String> listaVirus = new ArrayList<>();
		ArrayList<String> listaMedicina = new ArrayList<>();
		ArrayList<String> listaTratamiento = new ArrayList<>();

		insertMultipleValues(organo,listaOrgano,5);
		insertMultipleValues(virus,listaVirus,4);
		insertMultipleValues(medicina,listaMedicina,4);
		insertMultipleValues(tratamiento,listaTratamiento,2);
		deck.addAll(listaOrgano);
		deck.addAll(listaVirus);
		deck.addAll(listaMedicina);
		deck.addAll(listaTratamiento);
		deck.addAll(comodin);
		Collections.shuffle(deck);

		return deck;
	}



	void insertMultipleValues(List<String> listaInicial, ArrayList<String> listaDestino,int numVeces ){
		listaInicial.forEach((x) -> {
			for (int i = 0; i < numVeces; i++) {
				listaDestino.add(x);

			}
				});
	}

	@Test
	void dealCards(ArrayList<String> shuffledDeck, int numPlayers){
		ArrayList<String> player1 = new ArrayList<>();
		ArrayList<String> player2 = new ArrayList<>();
		ArrayList<String> player3 = new ArrayList<>();

		for (int x = 0; x < 3; x++) { //3 cartas para cada uno
			for (int i = 0; i < numPlayers; i++) { //por cada jugador
				System.out.println(shuffledDeck.get(0));
				if(i==0){
					player1.add(shuffledDeck.get(0));
				}else if(i==1){
					player2.add(shuffledDeck.get(0));
				}else if(i==2){
					player3.add(shuffledDeck.get(0));
				}
				shuffledDeck.remove(shuffledDeck.get(0));

			}
		}
		System.out.println(player1);//se reparte uno a uno en lugar de 3 cartas a 1 y luego al otro
		System.out.println(player2);
		System.out.println(player3);
	}

	@Test
	void game(){
		ArrayList<String> shuffledDeck = shuffle();
		dealCards(shuffledDeck,3);

	}
}
