package engine;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {
    Optional<Quiz> findQuizById(int id);
    List<Quiz> findAll();
}
