package com.games.cardGames.gameController;

import com.games.cardGames.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class Login {

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/login")
    private User authenticateUser(@RequestBody User user) {
        System.out.println(user.toString());
        String id = UUID.randomUUID().toString();
        User verrifUser = null;
        if(user.getUsername().equals("andres") && user.getPassword().equals("juegodecartas1")){
            verrifUser = new User(id,user.getUsername(),user.getPassword(),user.getRoom());
        }else {
            System.out.println("usuario erroneo");
        }
        return verrifUser;
    }
}
