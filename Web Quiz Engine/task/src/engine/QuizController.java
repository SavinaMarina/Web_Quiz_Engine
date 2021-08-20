package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<QuizDTO> quizzes() {
        return quizService.findAll().stream().map(QuizDTO::new).collect(Collectors.toList());
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

}
