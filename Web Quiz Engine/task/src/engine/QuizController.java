package engine;

import engine.persistence.Quiz;
import engine.persistence.QuizCompletionDTO;
import engine.persistence.QuizDTO;
import engine.persistence.QuizResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/{id}")
    public QuizDTO quiz(@PathVariable int id) {
        return new QuizDTO(quizService.findQuizById(id));
    }

    @GetMapping()
    public Page<QuizDTO> quizzes(@RequestParam(defaultValue = "0") Integer page) {
        return quizService.findAll(page).map(QuizDTO::new);
    }

    @PostMapping()
    public QuizDTO addQuiz(@RequestBody @Valid Quiz quiz) {
        return new QuizDTO(quizService.save(quiz));
    }

    @PostMapping("/{id}/solve")
    public QuizResults answerQuiz(@PathVariable int id, @RequestBody Map<String, int[]> answer) {
        return quizService.checkAndGetResults(id, answer.getOrDefault("answer", new int[0]));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable int id) {
        quizService.delete(quizService.findQuizById(id));
    }

    @GetMapping("/completed")
    public Page<QuizCompletionDTO> getStatistics(@RequestParam(defaultValue = "0") Integer page) {
        return quizService.getStatistics(page).map(QuizCompletionDTO::new);
    }
}
