package be.ehb.dactylokd.controller;
import be.ehb.dactylokd.exception.LetterNotFoundException;
import be.ehb.dactylokd.exception.UserNotFoundException;
import be.ehb.dactylokd.model.User;
import be.ehb.dactylokd.repository.UserRepository;
import com.sun.istack.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Validated
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping(value = {"", "/"})
    public Iterable<User> getAll(){
        return repository.findAll();
    }


    @GetMapping(value = "/{id}")
    @NotFound
    public User getById(@PathVariable Long id) throws UserNotFoundException {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException();
        }
    }
    @GetMapping(value = "/login/{username},{password}")
    @NotFound
    public User findByUsernameAndPassword(@PathVariable String username , @PathVariable String password ) throws UserNotFoundException {
        Optional<User> user = repository.findByUsernameAndPassword(username , password);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException();
        }
    }
    @GetMapping(value = "/search/{username}")
    @NotFound
    public User getByUsername(@PathVariable String username) throws UserNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException();
        }
    }
    @PostMapping(value = {"","/"})
    public User create(@Valid @RequestBody User user){
        return repository.save(user);
    }

    @PutMapping(value = "/{id}")
    @NotFound
    public User update(@NotNull @PathVariable Long id, @Valid @RequestBody User user) throws UserNotFoundException{
        Optional<User> s = repository.findById(id);
        if(s.isPresent()){
            user.setId(id);
            return repository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }

    @DeleteMapping(value = "/{id}")
    @NotFound
    public void delete(@NotNull @PathVariable Long id) throws UserNotFoundException {
        Optional<User> s = repository.findById(id);
        if(s.isPresent()){
            repository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }


}
