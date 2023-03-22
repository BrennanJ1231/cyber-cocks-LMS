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
    /*  ----??????----    WHY DO WE have TWO?   ---?????-----    public Course(String name, String description, Language language, UUID uuid){
                                this.name = name;
                                this.description = description;
                                this.language = language;
                                this.uuid = uuid;
                            }*/
    public Course(String name, String description, Language language, UUID uuid,ArrayList<Module> modules, ArrayList<Comment> comments) {
        this.name = name;
        this.modules = modules;
        this.description = description;
        this.language = language;
        this.uuid = uuid;
        this.comments =  comments;
    }
    public void addModule(Module module) {
        modules.add(module);
    }
    public void removeModule(Module modul) {
    modules.remove(modul);
    }
    public void addComments(ArrayList<Comment> comments) {
        this.comments.addAll(comments);
    }

}
