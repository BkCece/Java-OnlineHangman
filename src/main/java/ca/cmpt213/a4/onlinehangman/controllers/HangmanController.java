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

    @GetMapping("/")
    public String redirectFromRoot(){
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String showWelcomePage() {
        hangmanControllerInit();

        // take the user to welcome.html
        return "welcome";
    }

    // works like a constructor, but wait until dependency injection is done, so it's more like a setup
    // not really used here for hangman
    @PostConstruct
    public void hangmanControllerInit() {
        promptMessage = new Message("Initializing...");
    }

    @GetMapping("/game")
    public void redirectFromGetGame(){
        throw new GameNotFoundException();
    }

    // create-game for game
    @PostMapping("/game")
    public String createHangmanGame(Model model) {
        // Initialize game
        Game newGame = new Game(nextId.incrementAndGet());

        // Add new game to list and model
        // Should the model replace the need for a list?
        games.add(newGame);
        model.addAttribute("game", newGame);

        return "redirect:/game/" + newGame.getId().toString();
    }

    // Get game of specific id
    // Re-routes user to web page with specific game id
    @GetMapping("/game/{id}")
    public String getGameById(@PathVariable("id") int gameId,
                              Model model) {

        if (checkForExistingID(gameId)) {
            // Get specified game data
            // Game 1 has index 0, need to subtract 1
            Game currGame = games.get(gameId - 1);

            // Check if game has already ended
            if (!currGame.getStatus().equals("Active")) {
                //Redirect to game over page
                model.addAttribute("game", currGame);
                return "gameover";
            }

            model.addAttribute("game", currGame);

            return "game";

        } else {
            throw new GameNotFoundException();
        }
    }

    // Guess a letter in the word
    @RequestMapping(value = "/game/{id}", method = RequestMethod.POST)
    public String submitGuess(@PathVariable("id") int gameId, @ModelAttribute("Game") Game game, Model model) {

        if (checkForExistingID(gameId)) {
            // Get current game data
            Game currGame = games.get(gameId - 1);

            if (!currGame.getStatus().equals("Active")) {
                //Redirect to game over page
                model.addAttribute("game", currGame);
                return "gameover";
            }

            //Check guess for empty input
            if (game.getGuess() == null || game.getGuess().toString().equals(" ") || game.getGuess().toString().equals("")) {
                System.out.println("Player did not enter a guess");

            } else {

                // Set the current guess
                currGame.setGuess(game.getGuess().toString().toLowerCase().charAt(0));
                System.out.println("\nGuess: " + currGame.getGuess());

                // Check for correct guess & set word progress
                boolean guessResult = currGame.checkGuess();

                // Increase num incorrect guesses if no matches
                if (!guessResult) {
                    currGame.setNumIncorrectGuesses(currGame.getNumIncorrectGuesses() + 1);
                }

                // Increase number of guesses & set progress string
                currGame.setNumGuesses(currGame.getNumGuesses() + 1);
                currGame.setWordProgressString();

                System.out.println("Word: " + currGame.getWord());

                // Check game status
                if (currGame.getGameOverStatus()) {
                    //Redirect to game over page if inactive game
                    model.addAttribute("game", currGame);
                    return "gameover";
                }
            }

            model.addAttribute("game", currGame);
            return "redirect:/game/" + game.getId().toString();

        } else {
            throw new GameNotFoundException();
        }

    }

    @GetMapping("/helloworld")
    public String showHelloworldPage(Model model) {

        promptMessage.setMessage("You are at the helloworld page!");
        model.addAttribute("promptMessage", promptMessage);

        // take the user to helloworld.html
        return "helloworld";
    }

    /**
     * Helper function
     * Checks for a valid game id
     */
    public boolean checkForExistingID(int id) {
        boolean idExists = false;

        for (int m = 0; m < games.size(); m++) {
            if (id == games.get(m).getId()) {
                idExists = true;
                break;
            }
        }

        return idExists;
    }

    // Exception handler for invalid game id
    @ExceptionHandler(GameNotFoundException.class)
    public String GameNotFoundException() {
        return "gamenotfound";
    }
}