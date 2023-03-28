import java.util.ArrayList;

/**
Assignment class represents an assignment with questions and a grade.
*/
public class Assignment {
    protected String name;
    protected ArrayList<Question> questions;
    protected double grade;
    protected boolean completed;

    /**
    Constructs a new Assignment with the specified name.
    @param name the name of the assignment.
    */
    public Assignment(String name) {
        this.name = name;
    }

    /**
    Adds a single question to the assignment.
    @param question the question to be added.
    */
    public void addQuestion(Question question) {
        if(question != null) 
            this.questions.add(question);
    }

    /**
    Adds a list of questions to the assignment.
    @param questions the list of questions to be added.
    */
    public void addQuestions(ArrayList<Question> questions) {
        if(questions != null) 
            this.questions = questions;
    }
    /**
    Removes a question from the assignment.
    @param question the question to be removed.
    */
    public void removeQuestion(Question question) {
        for(int i = 0; i<questions.size(); i++) {
            if(questions.get(i).equals(question)) 
                questions.remove(i);
            
        }
    }

    /**
    Calculates the grade for the assignment based on the number of correct answers.
    @return the grade for the assignment.
    */
    public double calculateGrade() {
        int numRight = 0;
        for(int i = 0; i< questions.size(); i++) {
            if(questions.get(i).rightWrong) 
                numRight++;
        }
        return numRight/questions.size();
    }

}