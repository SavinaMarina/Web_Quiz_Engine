package engine.persistence;

import java.time.LocalDateTime;

public class QuizCompletionDTO {
    private int id;

    private LocalDateTime completedAt;

    public QuizCompletionDTO(QuizCompletion quizCompletion) {
        this.id = quizCompletion.getQuiz().getId();
        this.completedAt = quizCompletion.getCompletedAt();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

}
