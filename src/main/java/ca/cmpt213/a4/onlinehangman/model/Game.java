/**
 * Celina Wright
 * ckwright@sfu.ca
 * 301380112
 */

package ca.cmpt213.a4.onlinehangman.model;

/**
 * Class representing a game
 * Includes any classes that support the function of the application
 */
public class Game {
    // Auto-increment in controller using AtomicLong
    private Long id;

    // Game information
    private String status;
    private Integer numGuesses;
    private Integer numIncorrectGuesses;

    // The word to be guesses
    private String word;

    // A character array as a string to map each character in the chosen word to what is displayed
    // Initially contains only underscores in place of each character
    private String wordProgress;

    // Default constructor
    public Game(){

    }

    // Game constructor
    public Game(Long id, String status, Integer numGuesses, Integer numIncorrectGuesses, String word, String wordProgress) {
        this.id = id;
        this.status = status;
        this.numGuesses = numGuesses;
        this.numIncorrectGuesses = numIncorrectGuesses;
        this.word = word;
        this.wordProgress = wordProgress;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getNumGuesses() {
        return numGuesses;
    }

    public void setNumGuesses(Integer numGuesses) {
        this.numGuesses = numGuesses;
    }

    public Integer getNumIncorrectGuesses() {
        return numIncorrectGuesses;
    }

    public void setNumIncorrectGuesses(Integer numIncorrectGuesses) {
        this.numIncorrectGuesses = numIncorrectGuesses;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordProgress() {
        return wordProgress;
    }

    public void setWordProgress(String wordProgress) {
        this.wordProgress = wordProgress;
    }
}
