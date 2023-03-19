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
            this.questions.add(question);;
        } 
    }
    public void addQuestions(ArrayList<Question> questions) {
        if(questions != null) 
            this.questions = questions;
    }
    public void removeQuestion(Question question) {
        for(int i = 0; i<questions.size(); i++) {
            if(questions.get(i).equals(question)) {
                questions.remove(i);
            }
        }
    }
    public double calculateGrade() {
        int numRight = 0;
        for(int i = 0; i< questions.size(); i++) {
            if(questions.get(i).rightWrong) {
                numRight++;
            }
        }
        return numRight/questions.size();
    }

}
