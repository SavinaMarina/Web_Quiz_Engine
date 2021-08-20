package engine;

import java.util.List;
import java.util.stream.Collectors;

public class QuizDTO {

    private int id;
    private String title;
    private String text;
    private List<String> options;

    public QuizDTO(Quiz quiz) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.text = quiz.getText();
        options = quiz.getOptions().stream().map(QuizOption::getName).collect(Collectors.toUnmodifiableList());
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }
}
