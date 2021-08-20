package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class QuizDeletionForbiddenException extends RuntimeException {
    public QuizDeletionForbiddenException(){
        super("Quiz cannot be deleted. The user is not the author of this quiz!");
    }
}
