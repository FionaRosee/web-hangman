package htwwebtech.hangman.web.api;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class WordManipulationRequest {

    @Size(min = 2, message = "Please provide a word with 2 characters or more.")
    @NotBlank(message = "The word must not be empty")
    private String word;

    @Range(min = 1,max = 3, message = "The difficulty is out of range.")
    private int difficulty;

    private long id;
    private int length;

    public WordManipulationRequest(int id, int length, int difficulty, String word) {
        this.id = id;
        this.length = length;
        this.difficulty = difficulty;
        this.word = word;
    }

    public WordManipulationRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}

