package htwwebtech.hangman.web.api;

public class WordManipulationRequest {
    private long id;
    private int length;
    private int difficulty;
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public WordManipulationRequest(int id, int length, int difficulty) {
        this.id = id;
        this.length = length;
        this.difficulty = difficulty;
    }

    public WordManipulationRequest() {}

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
}

