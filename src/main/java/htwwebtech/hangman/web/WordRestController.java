package htwwebtech.hangman.web;


import htwwebtech.hangman.web.api.Word;
import htwwebtech.hangman.web.service.WordService;
import htwwebtech.hangman.web.api.WordManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class WordRestController {

    private final WordService wordService;

    public WordRestController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping(path = "/api/v1/words")
    public ResponseEntity<List<Word>> fetchWords() {
        System.out.println("Hier");
        var result = wordService.findAll();
        System.out.println(result);
        return ResponseEntity.ok(wordService.findAll());
    }


    @GetMapping(path = "/api/v1/words/{id}")
    public ResponseEntity<Word> fetchWordById(@PathVariable Long id) {
        var word = wordService.findById(id);
        return word != null ? ResponseEntity.ok(word) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/words")
    public ResponseEntity<Void> createWord(@Valid @RequestBody WordManipulationRequest request) throws URISyntaxException {
        var valid = validate(request);
        if (valid) {
            var word = wordService.create(request);
            URI uri = new URI("/api/v1/words/" + word.getId());
            return ResponseEntity.created(uri).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(path = "/api/v1/words/{id}")
    public ResponseEntity<Word> updateWord(@PathVariable Long id, @RequestBody WordManipulationRequest request) {
        var word = wordService.update(id, request);
        return word != null ? ResponseEntity.ok(word) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/words/{id}")
    public ResponseEntity<Void> deleteWord(@PathVariable Long id) {
        boolean successful = wordService.deleteById(id);
        return successful ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private boolean validate(WordManipulationRequest request) {
        return  request.getLength() > 1
                && request.getDifficulty() < 4 && request.getDifficulty() > 0
                && request.getWord() != null
                && !request.getWord().isBlank();

    }
}
