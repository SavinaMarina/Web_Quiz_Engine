package engine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException(int id) {
        super(String.format("Quiz %s: not found!", id));
    }
}
