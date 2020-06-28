package com.games.cardGames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardGamesApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(CardGamesApplication.class, args);
		}catch (Exception e){
			System.out.println(e.getStackTrace());
		}
	}



}
