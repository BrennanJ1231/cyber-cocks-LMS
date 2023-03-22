import java.util.ArrayList;

public class Assignment {
    protected String name;
    protected ArrayList<Question> questions;
    protected double grade;
    protected boolean completed;

    public Assignment(String name) {
        this.name = name;
    }
    public void addQuestion(Question question) {
        if(question != null) {
            this.questions.add(question);
        }
    }
    public void addQuestions(ArrayList<Question> questions) {
        
    }
    public void removeQuestion(Question question) {
       
    }
    public double calculateGrade() {
    
        return (Double) null;
    }

}