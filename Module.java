import java.util.ArrayList;

public class Module {
    protected String title;
    protected String description;
    protected ArrayList<InstructiveMaterial> material;
    protected ArrayList<Assignment> quizzes;
    protected ArrayList<Assignment> test;
    protected double completion;
    protected ArrayList<Comment> comments;
    public Module(String title, String description, ArrayList<Material> material) {

    }
    public void addAssignment(Assignment assignment, Type type) {
        if(type == Type.TEST) {
            test.add(assignment);
        }
        if(type == Type.QUIZ) {
            quizzes.add(assignment);
        }
        return;
    }
}
