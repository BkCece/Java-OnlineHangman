/**
 * Celina Wright
 * ckwright@sfu.ca
 * 301380112
 */

package ca.cmpt213.a4.onlinehangman.controllers;

import ca.cmpt213.a4.onlinehangman.model.Game;
import ca.cmpt213.a4.onlinehangman.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Class for handling all the get/post requests
 *
 * Uses Controller instead of RestController to avoid auto serialization
 */
@Controller
public class HangmanController {
    private Message promptMessage; //a reusable String object to display a prompt message at the screen
    private List<Game> games = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/welcome")
    public String showWelcomePage(){//Model model){

        //promptMessage.setMessage("You are at the welcome page!");
        //model.addAttribute("promptMessage", promptMessage);

        // take the user to welcome.html
        return "welcome";
    }


    // works like a constructor, but wait until dependency injection is done, so it's more like a setup
    @PostConstruct
    public void hangmanControllerInit() {

        promptMessage = new Message("Initializing...");
    }

    // create-game for game
    @PostMapping("/game")
    public String createHangmanGame(Model model){

        model.addAttribute("game", new Game());
        return "game";
    }

    @PostMapping("/save-game")
    public String saveHangmanGame(@ModelAttribute Game game){

        // TODO: dave game in DB here

        return "result";
    }

    @GetMapping("/helloworld")
    public String showHelloworldPage(Model model) {

        promptMessage.setMessage("You are at the helloworld page!");
        model.addAttribute("promptMessage", promptMessage);

        // take the user to helloworld.html
        return "helloworld";
    }
}
