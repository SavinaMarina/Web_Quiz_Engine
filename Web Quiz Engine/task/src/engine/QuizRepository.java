package engine;

import engine.persistence.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface QuizRepository extends PagingAndSortingRepository<Quiz, Integer> {
    Optional<Quiz> findQuizById(int id);
    Page<Quiz> findAll(Pageable pageable);
}
