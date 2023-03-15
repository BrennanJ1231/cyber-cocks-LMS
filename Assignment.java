import java.util.ArrayList;

public class Assignment {
    protected String name;
    protected ArrayList<Question> questions;
    protected Type type;
    protected double grade;
    public Assignment(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    public void addQuestion(Question question) {
        
    }
    public void addQuestions(ArrayList<Question> questions) {

    }
    public void removeQuestion(Question question) {
    
    }
    public double calculateGrade() {
        //TODO return a double 
    }

}
