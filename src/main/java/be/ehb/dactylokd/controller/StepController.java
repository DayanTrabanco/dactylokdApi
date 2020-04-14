package be.ehb.dactylokd.controller;

import be.ehb.dactylokd.exception.StepNotFoundException;
import be.ehb.dactylokd.exception.UserNotFoundException;
import be.ehb.dactylokd.model.Step;
import be.ehb.dactylokd.model.User;
import be.ehb.dactylokd.repository.StepRepository;
import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/steps")
@CrossOrigin(origins = "*")
@Validated
public class StepController {

    @Autowired
    private StepRepository repository;

    @GetMapping(value = {"", "/"})
    public Iterable<Step> getAll(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    @NotFound
    public Step getById(@PathVariable Long id) throws StepNotFoundException {
        Optional<Step> step = repository.findById(id);
        if(step.isPresent()){
            return step.get();
        } else {
            throw new StepNotFoundException();
        }
    }

    @PostMapping(value = {"","/"})
    public Step create(@Valid @RequestBody Step step){
        return repository.save(step);
    }

    @PutMapping(value = "/{id}")
    @NotFound
    public Step update(@NotNull @PathVariable Long id, @Valid @RequestBody Step step) throws UserNotFoundException {
        Optional<Step> s = repository.findById(id);
        if(s.isPresent()){
            step.setId(id);
            return repository.save(step);
        } else {
            throw new UserNotFoundException();
        }
    }

    @DeleteMapping(value = "/{id}")
    @NotFound
    public void delete(@NotNull @PathVariable Long id) throws UserNotFoundException {
        Optional<Step> s = repository.findById(id);
        if(s.isPresent()){
            repository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }

}
