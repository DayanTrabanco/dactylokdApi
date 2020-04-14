package be.ehb.dactylokd.repository;

import be.ehb.dactylokd.model.Step;
import be.ehb.dactylokd.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    public Iterable<User> findAllByUsernameContaining(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
