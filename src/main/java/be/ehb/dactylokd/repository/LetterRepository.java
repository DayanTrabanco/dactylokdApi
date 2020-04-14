package be.ehb.dactylokd.repository;

import be.ehb.dactylokd.model.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface LetterRepository extends CrudRepository<Letter, Long> {
    Iterable<Letter> findAllLettersByStepId(Long step_id);
    Iterable<Letter> findByStepIdLessThanEqual(Long step_id);
}
