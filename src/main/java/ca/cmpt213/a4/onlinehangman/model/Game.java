/**
 * Celina Wright
 * ckwright@sfu.ca
 * 301380112
 */

package ca.cmpt213.a4.onlinehangman.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    // The word to be guesses
    private String word;

    // A character list to map each character in the chosen word to what is displayed
    // Initially contains only underscores in place of each character
    private List<Character> wordProgress;
    private String wordProgressString;

    // Default constructor
    public Game(){

    }

    // Game constructor
    public Game(Long id, String status, Integer numGuesses, Integer numIncorrectGuesses, String word, List<Character> wordProgress, String wordProgressString) {
        this.id = id;
        this.status = status;
        this.numGuesses = numGuesses;
        this.numIncorrectGuesses = numIncorrectGuesses;
        this.word = word;
        this.wordProgress = wordProgress;
        this.wordProgressString = wordProgressString;
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

    public List<Character> getWordProgress() {
        return wordProgress;
    }

    public String getWordProgressString() {
        return wordProgressString;
    }

    public void setWordProgressString(List<Character> wordProgress) {
        wordProgressString = "";

        for (int j = 0; j < wordProgress.size(); j++){
            wordProgressString += wordProgress.get(j) + " ";
        }

        this.wordProgressString = wordProgressString;
    }

    public void setWordProgress(List<Character> wordProgress) {
        this.wordProgress = wordProgress;
    }

    public void initWordProgress() {
        // Get word length
        int wordLength = this.getWord().length();
        wordProgress = new ArrayList<>();

        // Set length to chosen word length
        //this.wordProgress = new Character[wordLength];

        for (int i = 0; i < wordLength; i++){
            wordProgress.add('_');
        }

        setWordProgress(wordProgress);
        //System.out.println("Word progress: " + wordProgress);
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
}
