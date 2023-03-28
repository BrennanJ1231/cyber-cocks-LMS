import java.util.*;

/**

Course class represents a course with a name, author, language, description, modules, comments, rating, completion, registration status, and UUID.
*/
public class Course {
    protected String name;
    protected Author author;
    protected Language language;
    protected String description;
    protected ArrayList<Module> modules;
    protected ArrayList<Comment> comments;
    protected double rating;
    protected double completion;
    protected boolean registered;
    protected UUID uuid;
 
    /**

    Constructs a new Course object with the specified name, description, language, UUID, modules, and comments.
    @param name the name of the course.
    @param description the description of the course.
    @param language the language of the course.
    @param uuid the UUID of the course.
    @param modules the ArrayList of modules of the course.
    @param comments the ArrayList of comments of the course.
    */
    public Course(String name, String description, Language language, UUID uuid,ArrayList<Module> modules, ArrayList<Comment> comments) {
        this.name = name;
        this.modules = modules;
        this.description = description;
        this.language = language;
        this.uuid = uuid;
        this.comments =  comments;
    }

    /*
     Adds a module to the course.
    @param module the module to be added.
     */
    public void addModule(Module module) {
        modules.add(module);
    }

    /**
    Removes a module from the course.
    @param module the module to be removed.
    */
    public void removeModule(Module modul) {
    modules.remove(modul);
    }

    /**
    Adds a list of comments to the course.
    @param comments the list of comments to be added.
    */
    public void addComments(ArrayList<Comment> comments) {
        this.comments.addAll(comments);
    }

}
