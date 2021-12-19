package htwwebtech.hangman.web.persistence;

import javax.persistence.*;

@Entity(name = "words")
public class WordEntity { //

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "length", nullable = false)
    private int length;

    @Column(name = "difficulty", nullable = false)
    private int difficulty;

    @Column(name = "word", nullable = false)
    private String word;



    public WordEntity(Long id, int length, int difficulty, String word) {
        this.id = id;
        this.length = length;
        this.difficulty = difficulty;
        this.word = word;
    }

    public WordEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
