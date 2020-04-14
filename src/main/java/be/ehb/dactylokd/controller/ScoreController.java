package be.ehb.dactylokd.controller;

import be.ehb.dactylokd.exception.ScoreNotFoundException;
import be.ehb.dactylokd.model.Score;
import be.ehb.dactylokd.repository.ScoreRepository;
import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/scores")
@CrossOrigin(origins = "*")
@Validated
public class ScoreController {
    @Autowired
    private ScoreRepository repository;

    @GetMapping(value = {"", "/"})
    public Iterable<Score> getAll(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    @NotFound
    public Score getById(@PathVariable Long id) throws ScoreNotFoundException {
        Optional<Score> score = repository.findById(id);
        if (score.isPresent()) {
            return score.get();
        } else {
            throw new ScoreNotFoundException();
        }
    }

    @GetMapping(value = "/user/{username}")
    @NotFound
    public Iterable<Score> getByUsername(@PathVariable String username) throws ScoreNotFoundException {
        Iterable<Score> score = repository.findByUsername(username);
        return score;
    }
    @GetMapping(value = "/step/{stepname}")
    @NotFound
    public Iterable<Score> getByStepname(@PathVariable String stepname) throws ScoreNotFoundException {
        Iterable<Score> score = repository.findByStepname(stepname);
        return score;
    }

    @GetMapping(value = "/top/{stepname}")
    @NotFound
    public Iterable<Score> getTop10Scores(@PathVariable String stepname) throws ScoreNotFoundException {
        Iterable<Score> score = repository.findTop10ScoresByStepnameOrderByScoreDesc(stepname);
        return score;
    }

    @GetMapping(value = "/both/{username},{stepname}")
    @NotFound
    public Score getByUsernameAndStepname(@PathVariable String username , @PathVariable String stepname) throws ScoreNotFoundException {
        Optional<Score> score = repository.findByUsernameAndStepname(username , stepname);
        if (score.isPresent()) {
            return score.get();
        } else {
            throw new ScoreNotFoundException();
        }
    }

    @PostMapping(value = {"","/"})
    public Score create(@Valid @RequestBody Score score){
        return repository.save(score);
    }

    @PutMapping(value = "/{id}")
    @NotFound
    public Score update(@NotNull @PathVariable Long id, @Valid @RequestBody Score score) throws ScoreNotFoundException {
        Optional<Score> s = repository.findById(id);
        if(s.isPresent()){
            score.setId(id);
            return repository.save(score);
        } else {
            throw new ScoreNotFoundException();
        }
    }

    @DeleteMapping(value = "/{id}")
    @NotFound
    public void delete(@NotNull @PathVariable Long id) throws ScoreNotFoundException {
        Optional<Score> s = repository.findById(id);
        if(s.isPresent()){
            repository.deleteById(id);
        } else {
            throw new ScoreNotFoundException();
        }
    }


}
