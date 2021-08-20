package engine;

public class QuizResults {
    private boolean success;
    private String feedback;

    private static final String MSG_SUCCESS = "Congratulations, you're right!";
    private static final String MSG_WRONG_ANSWER = "Wrong answer! Please, try again.";

    public QuizResults(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public QuizResults(boolean success) {
        this.success = success;
        this.feedback = success ? MSG_SUCCESS : MSG_WRONG_ANSWER;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
