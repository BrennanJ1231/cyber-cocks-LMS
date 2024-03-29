import java.util.ArrayList;
/**
 * A class that represents a module in a course
 */
public class Module {
    protected String title;
    protected String description;
    protected ArrayList<InstructiveMaterial> material;
    protected ArrayList<Assignment> test;
    protected double completion;
    protected ArrayList<Comment> comments;

    /**
    Constructor for Module class.
    @param title the title of the module.
    @param description the description of the module.
    @param material an ArrayList of instructive materials in the module.
    */
    public Module(String title, String description, ArrayList<InstructiveMaterial> material, ArrayList<Assignment> assignments) {
        this.title = title;
        this.description = description;
        this.material = material;
        this.test = assignments;
    }

    /**
    Adds an assignment to the test.
    @param assignment the assignment to add.
    */
    public void addAssignment(Assignment assignment){
        if(assignment != null)
        test.add(assignment);
    }
   
    /**
    Adds multiple assignments.
    @param assignments an ArrayList of assignments to add.
    */
    public void addAssignments(ArrayList<Assignment> assignments) {
        test = assignments;
    }

    /**
    Finds an assignment with the specified name in the list of assignments in the module.
    @param name The name of the assignment to find.
    @return The Assignment object with the specified name, or null if not found.
    */
    public Assignment findAssignment(String name) {
        for (int i=0; i<test.size();i++) {
            if (test.get(i).name.equalsIgnoreCase(name)) {
                return test.get(i);
            }
        }
        return null;
    }

    /**
    Finds an instructive material with the specified name in the list of instructive materials in the module.
    @param name The name of the instructive material to find.
    @return The InstructiveMaterial object with the specified name, or null if not found.
    */
    public InstructiveMaterial findMaterial(String name) {
        for (int i=0; i<material.size();i++) {
            if (material.get(i).name.equalsIgnoreCase(name)) {
                return material.get(i);
            }
        }
        return null;
    }
}
