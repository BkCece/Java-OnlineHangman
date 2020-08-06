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
        hangmanControllerInit();

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
        // Initialize game
        Game newGame = new Game();
        newGame.setId(nextId.incrementAndGet());
        newGame.setStatus("Active");
        newGame.setNumGuesses(0);
        newGame.setNumIncorrectGuesses(0);
        newGame.getNewWord();
        newGame.initWordProgress();
        newGame.setWordProgressString(newGame.getWordProgress());
        //newGame.setWordProgress();

        // Add new game to list and model
        // Should the model replace the need for a list?
        games.add(newGame);
        model.addAttribute("game", newGame);

        // Update game

        return "redirect:/game/" + newGame.getId().toString();
    }

    // Get game of specific id
    // Re-routes user to web page with specific game id
    @GetMapping("/game/{id}")
    public String getGameById(@PathVariable("id") int gameId,
                            Model model){

        // Get specified game data
        // Game 1 has index 0, need to subtract 1
        Game currGame = games.get(gameId - 1);
        model.addAttribute("game", currGame);

        System.out.println("Trying a thing, game id: " + currGame.getId() + currGame.getWord());

        return "game";
    }

    // Guess a letter in the word
    @RequestMapping(value="/game/{id}", method = RequestMethod.POST)
    public String submitGuess(@PathVariable("id") int gameId, @ModelAttribute("Game") Game game, Model model){

        // Get current game data
        Game currGame = games.get(gameId - 1);

        // Set the current guess
        currGame.setGuess(game.getGuess().toString().toLowerCase().charAt(0));
        model.addAttribute("game", currGame);
        System.out.println("Guess: " + currGame.getGuess());

        boolean correctGuess = false;

        // Search the word for the guess
        for(int k = 0; k < currGame.getWord().length(); k++){
            if(currGame.getGuess() == currGame.getWord().charAt(k)){
                // Set to true if it's a match
                currGame.getWordProgress().set(k, true);
                correctGuess = true;
            }
        }

        // Increase num incorrect guesses if no matches
        if(!correctGuess){
            currGame.setNumIncorrectGuesses(currGame.getNumIncorrectGuesses() + 1);
        }

        // Increase number of guesses
        currGame.setNumGuesses(currGame.getNumGuesses() + 1);

        // Set the new word progress string
        currGame.setWordProgressString(currGame.getWordProgress());

        System.out.println("Word: " + currGame.getWord());
        System.out.println("Word progress: " + currGame.getWordProgress());
        System.out.println("WPS: " + currGame.getWordProgressString());

        return "redirect:/game/" + game.getId().toString();
    }

    /**
    @PostMapping("/save-game")
    public String saveHangmanGame(@ModelAttribute Game game){

        // TODO: save game in DB here

        return "result";
    }
    **/

    @GetMapping("/helloworld")
    public String showHelloworldPage(Model model) {

        promptMessage.setMessage("You are at the helloworld page!");
        model.addAttribute("promptMessage", promptMessage);

        // take the user to helloworld.html
        return "helloworld";
    }
}
