import java.util.ArrayList;

public class Module {
    protected String title;
    protected String description;
    protected ArrayList<InstructiveMaterial> material;
    protected ArrayList<Assignment> test;
    protected double completion;
    protected ArrayList<Comment> comments;
    /**
     * Constructor for the Module Class
     * @param title Module title
     * @param description Describes what the module does
     * @param material Materials in the module
     */
    public Module(String title, String description, ArrayList<InstructiveMaterial> material) {
        this.title = title;
        this.description = description;
        this.material = material;
    }

    public void addAssignment(Assignment assignment) {
        if(assignment != null)
            test.add(assignment);
    }
    public void addAssignments(ArrayList<Assignment> assignments) {
        test = assignments;
    }
}
