package engine.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.security.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "QuizID", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<QuizAnswer> answer = new ArrayList<>();

    @NotNull
    @Size(min = 2)
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "QuizID", nullable = false)
    private List<QuizOption> options = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<QuizCompletion> quizCompletions = new ArrayList<>();;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    @JsonIgnore
    private User user;

    public Quiz() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<QuizAnswer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<QuizAnswer> answer) {
        this.answer = answer;
    }

    public List<QuizOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuizOption> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
