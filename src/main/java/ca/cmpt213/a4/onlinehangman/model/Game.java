/**
 * Celina Wright
 * ckwright@sfu.ca
 * 301380112
 */

package ca.cmpt213.a4.onlinehangman.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    // A character guess
    private Character guess;

    // The word to be guesses
    private String word;

    // A character list to map each character in the chosen word to what is displayed
    // Initially contains only underscores in place of each character
    private List<Boolean> wordProgress;
    private String wordProgressString;

    // Default constructor
    public Game(){

    }

    // Game constructor
    public Game(Long id) {
        this.id = id;
        this.status = "Active";
        this.numGuesses = 0;
        this.numIncorrectGuesses = 0;
        this.guess = null;
        getNewWord();
        initWordProgress();
        setWordProgressString();
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

    public Character getGuess() {
        return guess;
    }

    public void setGuess(Character guess) {
        this.guess = guess;
    }

    public void setWordProgressString(String wordProgressString) {
        this.wordProgressString = wordProgressString;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void getNewWord(){
        List<String> words = new ArrayList<>();
        try{
            // Read all words in file
            File wordFile = new File("OnlineHangman\\src\\commonWords.txt");
            //System.out.println("File created");

            Scanner scanner = new Scanner(wordFile);
            //System.out.println("scanner read");
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());

            }
            //System.out.println("Words: " + words);
            //System.out.println("Num of words: " + words.size());
            scanner.close();

            // Choose a random word from the list
            int randomIndex = (int)(Math.random() * (((words.size() -1) - 0) + 1)) + 0;
            //System.out.println("Chosen index" + randomIndex);

            setWord(words.get(randomIndex));

            //System.out.println("chosen word:" + word);

            setWord(word);

        }catch (Exception ex){
            System.out.println("ERROR LOADING NEW WORD FROM FILE");
        }
    }

    public List<Boolean> getWordProgress() {
        return wordProgress;
    }

    public void setWordProgress(List<Boolean> wordProgress) {
        this.wordProgress = wordProgress;
    }

    public void initWordProgress() {
        // Get word length
        int wordLength = this.getWord().length();
        wordProgress = new ArrayList<>();

        // Set length to chosen word length
        //this.wordProgress = new Character[wordLength];

        // Initialize each character to false - not guessed
        for (int i = 0; i < wordLength; i++){
            wordProgress.add(false);
            /**
             * TESTING
             * CHANGE BACK LATER
             * wordProgress.add(false);
             */
        }

        setWordProgress(wordProgress);
        //System.out.println("Word progress: " + wordProgress);
    }

    public String getWordProgressString() {
        return wordProgressString;
    }

    public void setWordProgressString() {
        // Reset the string
        wordProgressString = "";

        // Check mapping to see if each letter has been guessed yet
        for (int j = 0; j < wordProgress.size(); j++){
            if(wordProgress.get(j)){
                wordProgressString += word.charAt(j);
            }else{
                wordProgressString += "_ ";
            }
        }

        this.wordProgressString = wordProgressString;
    }

    public boolean checkGuess(){
        Boolean correctGuess = false;

        // Search the word for the guess
        for(int k = 0; k < word.length(); k++){
            if(guess == word.charAt(k)){
                // If already set, guess is incorrect
                if(wordProgress.get(k)){
                    return correctGuess;
                }else{
                    // Set to true if it's a match
                    wordProgress.set(k, true);
                    correctGuess = true;
                }
            }
        }

        return correctGuess;
    }

    public boolean getGameOverStatus(){
        if(wordIsCompleted()){
            // Get win or loss conditions
            if(numIncorrectGuesses > 7){
                status = "Lost";
            }else{
                status = "Won";
            }

            System.out.println("Game over");
            return true;
        }else{
            if (numIncorrectGuesses == 7){
                status = "Lost";
            }
        }

        System.out.println("Game not over");
        return false;
    }

    public boolean wordIsCompleted(){
        Boolean completed = true;

        for(int l = 0; l < wordProgress.size(); l++){
            if(!wordProgress.get(l)){
                completed = false;
                break;
            }
        }
        System.out.println("Completed: " + completed);

        return completed;
    }

}
