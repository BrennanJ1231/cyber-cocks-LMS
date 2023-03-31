import java.util.ArrayList;

/**
Represents a question
*/
public class Question {
    protected String question;
    protected ArrayList<String> choices;
    protected String correctAnswer;
    protected String userAnswer;
    protected boolean rightWrong;

    /**
    Constructs a Question object with the specified question, choices and correct answer.
    @param question the question
    @param choices the choices for the question
    @param correctAnswer the correct answer to the question
    */
    public Question(String question, ArrayList<String> choices, String correctAnswer) {
        this.question = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
        this.rightWrong = false;
    }

    /**
    Adds a choice to the question.
    @param choice the choice to be added
    */
    public void addChoice(String choice) {
        choices.add(choice);
    }

    /**
    Removes a choice from the question.
    @param choice the choice to be removed
    */
    public void removeChoice(String choice) {
        choices.remove(choice);
    }

    /**
    Compares this Question to the specified Question.
    @param question the Question to compare
    @return true if the Questions are equal; false otherwise
    */
    public boolean equals(Question question) {
        return this.question.equals(question.question) && this.choices.containsAll(question.choices) && this.correctAnswer.equals(question.correctAnswer);
    }

    /**
    Sets the question.
    @param questionName the name of the question to be set
    */
    public void setQuestion(String questionName) {
        this.question = questionName;
    }
    
}
