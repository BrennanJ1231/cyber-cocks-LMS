import java.util.ArrayList;

public class Question {
    protected String question;
    protected ArrayList<String> choices;
    protected String correctAnswer;
    protected String userAnswer;
    protected boolean rightWrong;
    public Question(String question, ArrayList<String> choices, String correctAnswer) {
        this.question = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.rightWrong = false;
    }
    public void addChoice(String choice) {
        choices.add(choice);
    }
    public void removeChoice(String choice) {
        choices.remove(choice);
    }
    public boolean equals(Question question) {
        return this.question.equals(question.question) && this.choices.containsAll(question.choices) && this.correctAnswer.equals(question.correctAnswer);
    }

    public void setQuestion(String questionName) {
        this.question = questionName;
    }
    
}
