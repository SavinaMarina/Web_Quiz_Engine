package engine;

import engine.exceptions.QuizDeletionForbiddenException;
import engine.exceptions.QuizNotFoundException;
import engine.persistence.Quiz;
import engine.persistence.QuizAnswer;
import engine.persistence.QuizCompletion;
import engine.persistence.QuizResults;
import engine.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class QuizService {

    private final UserService userService;
    private final QuizRepository quizRepository;
    private final QuizCompletionRepository quizCompletionRepository;

    @Autowired
    public QuizService(UserService userService, QuizRepository quizRepository,
                       QuizCompletionRepository quizCompletionRepository) {
        this.userService = userService;
        this.quizRepository = quizRepository;
        this.quizCompletionRepository = quizCompletionRepository;
    }

    public Quiz findQuizById(int id) {
        return quizRepository.findQuizById(id)
                .orElseThrow(() -> {throw new QuizNotFoundException(id);} );
    }

    public Page<Quiz> findAll(Integer page) {
        Pageable paging = PageRequest.of(page, 10);
        return quizRepository.findAll(paging);
    }

    public Quiz save(Quiz quiz) {
        quiz.setUser(userService.getCurrentUser());
        return quizRepository.save(quiz);
    }

    public QuizResults checkAndGetResults(int id, int[] answers) {
        QuizResults qr = new QuizResults(Arrays.equals(
                answers,
                findQuizById(id).getAnswer().stream().mapToInt(QuizAnswer::getValue).toArray()));
        if (qr.isSuccess()) {
            quizCompletionRepository.save(new QuizCompletion(findQuizById(id),
                    userService.getCurrentUser()));
        }
        return qr;
    }

    public void delete(Quiz q) {
        if (q.getUser().equals(userService.getCurrentUser())) {
            quizRepository.delete(q);
        }
        else {
            throw new QuizDeletionForbiddenException();
        }
    }

    public Page<QuizCompletion> getStatistics(Integer page) {
        Pageable paging = PageRequest.of(page, 10, Sort.by("completedAt").descending());
        return quizCompletionRepository.findByUser(userService.getCurrentUser(),
                paging);
    }

}