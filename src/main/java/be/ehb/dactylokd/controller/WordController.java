package be.ehb.dactylokd.controller;

import be.ehb.dactylokd.exception.WordNotFoundException;
import be.ehb.dactylokd.model.Word;
import be.ehb.dactylokd.repository.WordRepository;
import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/words")
@CrossOrigin(origins = "*")
@Validated
public class WordController {
    @Autowired
    private WordRepository repository;

    @GetMapping(value = {"", "/"})
    public Iterable<Word> getAll(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    @NotFound
    public Word getById(@PathVariable Long id) throws WordNotFoundException {
        Optional<Word> word = repository.findById(id);
        if (word.isPresent()) {
            return word.get();
        } else {
            throw new WordNotFoundException();
        }
    }

    @GetMapping(value = "/level/{level}")
    @NotFound
    public Iterable<Word> getByLevel(@PathVariable int level) throws WordNotFoundException {
        Iterable<Word>  word = repository.findByLevel(level);
        return word;
    }

    @GetMapping(value = "/word/{word}")
    @NotFound
    public Iterable<Word> getByWord(@PathVariable String word) throws WordNotFoundException {
        Iterable<Word> words = repository.findByWord(word);
        return words;
    }

    @GetMapping(value = "/language/{language}")
    @NotFound
    public Iterable<Word> getByLanguage(@PathVariable String language) throws WordNotFoundException {
        Iterable<Word> word = repository.findByLanguage(language);
        return word;
    }

    @GetMapping(value = "/both/{level},{language}")
    @NotFound
    public Iterable<Word> getByLevelAndLanguage(@PathVariable int level, @PathVariable String language) throws WordNotFoundException {
        Iterable<Word> word = repository.findByLevelAndLanguage(level, language);
        return word;
    }



    @PostMapping(value = {"","/"})
    public Word create(@Valid @RequestBody Word word){
        return repository.save(word);
    }

    @PutMapping(value = "/{id}")
    @NotFound
    public Word update(@NotNull @PathVariable Long id, @Valid @RequestBody Word word) throws WordNotFoundException {
        Optional<Word> s = repository.findById(id);
        if(s.isPresent()){
            word.setId(id);
            return repository.save(word);
        } else {
            throw new WordNotFoundException();
        }
    }

    @DeleteMapping(value = "/{id}")
    @NotFound
    public void delete(@NotNull @PathVariable Long id) throws WordNotFoundException {
        Optional<Word> s = repository.findById(id);
        if(s.isPresent()){
            repository.deleteById(id);
        } else {
            throw new WordNotFoundException();
        }
    }
}
