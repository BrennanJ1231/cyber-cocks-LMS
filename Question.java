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
    }
    public void addChoice(String choice) {
        choices.add(choice);
    }
    public void removeChoice(String choice) {
        choices.remove(choice);
    }
    
}
