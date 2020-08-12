package be.ehb.dactylokd.repository;

import be.ehb.dactylokd.model.Score;
import be.ehb.dactylokd.model.Word;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WordRepository extends CrudRepository<Word, Long> {
    Iterable<Word> findByLevel(int level);
    Iterable<Word> findByLanguage(String language);
    Iterable<Word> findByWord(String word);

    Iterable<Word> findByLevelAndLanguage(int level, String language);
}
