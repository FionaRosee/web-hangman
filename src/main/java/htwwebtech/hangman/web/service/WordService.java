package htwwebtech.hangman.web.service;

import htwwebtech.hangman.web.persistence.WordEntity;
import htwwebtech.hangman.web.persistence.WordRepository;
import htwwebtech.hangman.web.api.Word;
import htwwebtech.hangman.web.api.WordManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> findAll() {
        List<WordEntity> words = wordRepository.findAll();
        return words.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Word findById(Long id) {
        var wordEntity = wordRepository.findById(id);
        return wordEntity.map(this::transformEntity).orElse(null);
    }

    public Word create(WordManipulationRequest request) {
        var wordEntity = new WordEntity( request.getId(), request.getLength(), request.getDifficulty(), request.getWord());
        wordEntity = wordRepository.save(wordEntity);
        return transformEntity(wordEntity);
    }

    public Word update(Long id, WordManipulationRequest request) {
        var wordEntityOptional = wordRepository.findById(id);
        if (wordEntityOptional.isEmpty()) {
            return null;
        }

        var wordEntity = wordEntityOptional.get();
        wordEntity.setLength(request.getLength());
        wordEntity.setDifficulty(request.getDifficulty());
        wordEntity.setWord(request.getWord());
        wordEntity = wordRepository.save(wordEntity);

        return transformEntity(wordEntity);
    }

    public boolean deleteById(Long id) {
        if (!wordRepository.existsById(id)) {
            return false;
        }

        wordRepository.deleteById(id);
        return true;
    }

    private Word transformEntity(WordEntity wordEntity) {
        return new Word(
                wordEntity.getId(),
                wordEntity.getLength(),
                wordEntity.getDifficulty(),
                wordEntity.getWord()
        );
    }
}




