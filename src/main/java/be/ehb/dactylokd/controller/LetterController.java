package be.ehb.dactylokd.controller;

import be.ehb.dactylokd.exception.LetterNotFoundException;
import be.ehb.dactylokd.exception.UserNotFoundException;
import be.ehb.dactylokd.model.Letter;
import be.ehb.dactylokd.model.User;
import be.ehb.dactylokd.repository.LetterRepository;
import be.ehb.dactylokd.repository.StepRepository;
import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/letters")
@CrossOrigin(origins = "*")
@Validated
public class LetterController {

   @Autowired
    private LetterRepository repository;

    @Autowired
    private StepRepository stepRepository;

  /* @GetMapping("/steps/{step_id}/letters")
    public List<Letter> getAllLettersByStepId(@PathVariable (value = "step_id") Long stepId) {
        return repository.findByStepId(stepId);
    } */


    @GetMapping(value = {"", "/"})
    public Iterable<Letter> getAll(){
        return repository.findAll();
    }

    @GetMapping(value = "/step/{step_id}")
    public Iterable<Letter> getAllLettersByStepId(@PathVariable Long step_id){
        return repository.findAllLettersByStepId(step_id);
    }

    @GetMapping(value = "/smaller/{step_id}")
    public Iterable<Letter> getLettersBySmallerStepId(@PathVariable Long step_id){
        return repository.findByStepIdLessThanEqual(step_id);
    }
    @GetMapping(value = "/{id}")
    @NotFound
    public Letter getById(@PathVariable Long id) throws LetterNotFoundException {
        Optional<Letter> letter = repository.findById(id);
        if (letter.isPresent()) {
            return letter.get();
        } else {
            throw new LetterNotFoundException();
        }
    }
    @PostMapping(value = {"","/"})
    public Letter create(@Valid @RequestBody Letter letter){
        return repository.save(letter);
    }

    @PutMapping(value = "/{id}")
    @NotFound
    public Letter update(@NotNull @PathVariable Long id, @Valid @RequestBody Letter letter) throws LetterNotFoundException {
        Optional<Letter> s = repository.findById(id);
        if(s.isPresent()){
            letter.setId(id);
            return repository.save(letter);
        } else {
            throw new LetterNotFoundException();
        }
    }

    @DeleteMapping(value = "/{id}")
    @NotFound
    public void delete(@NotNull @PathVariable Long id) throws LetterNotFoundException {
        Optional<Letter> s = repository.findById(id);
        if(s.isPresent()){
            repository.deleteById(id);
        } else {
            throw new LetterNotFoundException();
        }
    }
}
