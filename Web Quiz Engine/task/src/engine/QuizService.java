package engine;

import engine.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    UserService userService;

    @Autowired
    private QuizRepository quizRepository;

    public Quiz findQuizById(int id) {
        return quizRepository.findQuizById(id)
                .orElseThrow(() -> {throw new QuizNotFoundException(id);} );
    }

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Quiz save(Quiz quiz) {
        quiz.setUser(userService.getCurrentUser());
        return quizRepository.save(quiz);
    }

    public QuizResults checkAndGetResults(int id, int[] answers) {
        return new QuizResults(Arrays.equals(
                answers,
                findQuizById(id).getAnswer().stream().mapToInt(QuizAnswer::getValue).toArray()));
    }

    public void delete(Quiz q) {
        if (q.getUser().equals(userService.getCurrentUser())) {
            quizRepository.delete(q);
        }
        else {
            throw new QuizDeletionForbiddenException();
        }
    }

}