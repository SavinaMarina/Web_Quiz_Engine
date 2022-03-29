package engine;

import engine.persistence.QuizCompletion;
import engine.security.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface QuizCompletionRepository extends PagingAndSortingRepository<QuizCompletion, Integer> {
    Page<QuizCompletion> findByUser(User user, Pageable pageable);
}
