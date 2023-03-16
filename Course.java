import java.util.*;

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
    public Course(String name, Author author, String description, Language language, UUID uuid) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.language = language;
        this.uuid = uuid;
    }
    public Course(String name, String description, Language language){

    }
    public void addModules(ArrayList<Module> modules) {

    }
    public void addModule(Module module) {

    }
    public void removeModule(Module modul) {

    }
    public void addComments(ArrayList<Comment> comments) {

    }
}
