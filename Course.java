import java.util.ArrayList;

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
    public Course(String name, Author author, String description, Language language) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.language = language;
    }
    public void addModule() {

    }
    public void removeModule() {

    }
}
