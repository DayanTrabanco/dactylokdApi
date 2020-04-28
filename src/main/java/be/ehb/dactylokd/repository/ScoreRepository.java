package be.ehb.dactylokd.repository;

import be.ehb.dactylokd.model.Score;
import be.ehb.dactylokd.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ScoreRepository extends CrudRepository<Score, Long> {
    Iterable<Score> findByUsername(String username);
    Iterable<Score> findByStepname(String stepname);
    Optional<Score> findByUsernameAndStepname(String username , String stepname);

    Iterable<Score> findTop10ScoresByStepnameOrderByScoreDesc(String stepname);
    Iterable<Score> findTop10ScoresByStepnameAndUsernameOrderByScoreDesc(String stepname , String username);
}
